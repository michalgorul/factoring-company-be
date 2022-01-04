package com.polsl.factoringcompany.credit;

import com.polsl.factoringcompany.exceptions.IdNotFoundInDatabaseException;
import com.polsl.factoringcompany.transaction.TransactionRequestDto;
import com.polsl.factoringcompany.transaction.TransactionService;
import com.polsl.factoringcompany.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * The type credit service. Used to connect controller with Data access object
 *
 * @author Michal Goral
 * @version 1.0
 */
@Service
@RequiredArgsConstructor
public class CreditService {

    /**
     * the credit repository bean
     */
    private final CreditRepository creditRepository;

    /**
     * the user service bean
     */
    private final UserService userService;

    /**
     * the transaction service bean
     */
    private final TransactionService transactionService;

    /**
     * Gets all credits from database.
     *
     * @return the credits
     */
    public List<CreditEntity> getCredits() {
        return this.creditRepository.findAll();
    }

    /**
     * Gets credit specified by id. If nothing found throws IdNotFoundInDatabaseException
     *
     * @param id the id
     * @return the credit
     */
    public CreditEntity getCredit(Long id) {
        return this.creditRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundInDatabaseException("Credit", id));
    }

    /**
     * Gets credit number of credit specified by id. If credit not found returns nul
     *
     * @param id the id
     * @return the credit number
     */
    public String getCreditNumber(Long id) {
        CreditEntity creditEntity = this.creditRepository.findById(id).orElse(null);
        return creditEntity != null ? creditEntity.getCreditNumber() : null;
    }

    /**
     * Gets credit specified by number. If nothing found throws IdNotFoundInDatabaseException
     *
     * @param creditNumber the credit number
     * @return the credit bu number
     */
    public CreditEntity getCreditBuNumber(String creditNumber) {
        return this.creditRepository.findByCreditNumber(creditNumber)
                .orElseThrow(() -> new IdNotFoundInDatabaseException("Credit", 0L));
    }

    /**
     * Deletes credit specified by id.
     *
     * @param id the id
     */
    public void deleteCredit(Long id) {
        try {
            this.creditRepository.deleteById(id);
        } catch (RuntimeException ex) {
            throw new IdNotFoundInDatabaseException("Credit", id);
        }
    }

    /**
     * Gets credits of currently logged user in JWT token.
     *
     * @return the credits of current user
     */
    public List<CreditEntity> getCreditsCurrentUser() {
        Long currentUserId = userService.getCurrentUserId();
        return this.creditRepository.findAllByUserId(Math.toIntExact(currentUserId));
    }

    /**
     * Gets the amount that current user has left to pay.
     *
     * @return the left to pay
     */
    public Double getLeftToPay() {
        Long currentUserId = userService.getCurrentUserId();

        List<CreditEntity> allByUserId = this.creditRepository.findAllByUserId(Math.toIntExact(currentUserId));

        DecimalFormat df = new DecimalFormat("#.##");
        return Double.valueOf(df.format(allByUserId.stream()
                .map(CreditEntity::getBalance)
                .mapToDouble(BigDecimal::doubleValue).sum()).replace(",", "."));

    }

    /**
     * Creates current user a credit entity.
     *
     * @param creditRequestDto the credit request dto
     * @return the credit entity
     */
    public CreditEntity createCurrentUserCredit(CreditRequestDto creditRequestDto) {
        try {
            String newCreditNumber = getNewCreditNumber();
            userService.getCurrentUserId();

            return this.creditRepository.save(new CreditEntity(
                    creditRequestDto, newCreditNumber, Math.toIntExact(userService.getCurrentUserId())));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates a credit number for a new credit entity
     * @return a new credit number
     */
    private String getNewCreditNumber() {
        StringBuilder newCreditNumber = new StringBuilder();
        Formatter formatter = new Formatter(newCreditNumber);
        int month = LocalDateTime.now().getMonthValue();
        int year = LocalDateTime.now().getYear();
        long lastCreditIdPlusOne = 1L;

        try {
            lastCreditIdPlusOne = creditRepository.getCreditNumber(month, year) + 1;
        } catch (NullPointerException ignored) {
        }
        formatter.format("%d/%d/%d", lastCreditIdPlusOne, month, year);

        return newCreditNumber.toString();
    }

    /**
     * Updates status of credit from in review to active.
     * It also creates a transaction entity which has benefit of whole credit amount
     * @param creditEntity the credit entity
     */
    private void updateFromInReviewToActive(CreditEntity creditEntity) {
        try {
            creditEntity.setStatus("active");
            this.creditRepository.save(creditEntity);

            transactionService.addTransaction(new TransactionRequestDto(
                            creditEntity.getAmount(),
                            true,
                            "Loan receive",
                            null,
                            Math.toIntExact(creditEntity.getId())),
                    creditEntity.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates status of credit from active to funded.
     * @param creditEntity the credit entity
     */
    private void updateFromActiveToFunded(CreditEntity creditEntity) {
        try {
            creditEntity.setStatus("funded");
            this.creditRepository.save(creditEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if proper file is uploaded to database in order to make credit entity status 'in review'
     * @param fileName the file name of credit document
     * @return the name of file and credit number in HashMap
     */
    private HashMap<String, String> ifProperFileUploadedAndReturnNameAndCreditNumber(String fileName) {
        String patterns = "\\S*[_]\\S*[_]\\d{1,2}[_]\\d{1,2}[_]\\d{4}.pdf";
        Pattern pattern = Pattern.compile(patterns);
        if (pattern.matcher(fileName).matches()) {
            String name = fileName.replaceAll("(\\S*)[_](\\S*)[_]\\d{1,2}[_]\\d{1,2}[_]\\d{4}.pdf", "$1 $2");
            String creditNumber = fileName.replaceAll("(\\S*)[_](\\S*)[_](\\d{1,2}[_]\\d{1,2}[_]\\d{4}).pdf", "$3");
            creditNumber = creditNumber.replaceAll("_", "/");
            HashMap<String, String> returnMap = new HashMap<>();
            returnMap.put("name", name);
            returnMap.put("creditNumber", creditNumber);
            return returnMap;
        } else
            return null;
    }

    /**
     * Updates credit status from processing to in review.
     * It happens if user uploads proper file to factoring company database
     *
     * @param fileName the file name
     */
    public void updateFromProcessingToInReview(String fileName) {
        try {
            HashMap<String, String> mapToCheck = ifProperFileUploadedAndReturnNameAndCreditNumber(fileName);
            if (mapToCheck != null) {
                Optional<CreditEntity> creditEntity = this.creditRepository.findByCreditNumber(mapToCheck.get("creditNumber"));
                if (creditEntity.isPresent() &&
                        (userService.getUser((long) creditEntity.get().getUserId()).getFirstName() + " " +
                                userService.getUser((long) creditEntity.get().getUserId()).getLastName())
                                .equals(mapToCheck.get("name"))) {
                    creditEntity.get().setStatus("review");
                    this.creditRepository.save(creditEntity.get());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Update credit statuses. It happens every day at 00.01
     */
    //@Scheduled(fixedRate = 15000)
    //    Fires at 12 PM every day:
    @Scheduled(cron = "0 1 0 * * ?")
    public void updateCreditStatuses() {
        List<CreditEntity> allByStatusEqualsReview = this.creditRepository.findAllByStatusEquals("review");
        for (CreditEntity creditEntity : allByStatusEqualsReview) {
            updateFromInReviewToActive(creditEntity);
        }

        List<CreditEntity> allByStatusEqualsActive = this.creditRepository.findAllByStatusEquals("active");
        for (CreditEntity creditEntity : allByStatusEqualsActive) {
            if (creditEntity.getLastInstallmentDate().before(Date.valueOf(LocalDateTime.now().toLocalDate())))
                updateFromActiveToFunded(creditEntity);
        }
        System.out.println("credit statuses updated");
    }

    /**
     * Gets schedule of credit entity.
     *
     * @param id the id
     * @return the schedule
     */
    public List<CreditSchedule> getSchedule(Long id) {
        Optional<CreditEntity> byId = this.creditRepository.findById(id);
        return byId.map(CreditSchedule::getSchedule).orElse(null);
    }

    /**
     * Adds standard payment to credit entity. So the paid by user amount increases and left to pay decreases
     *
     * @param id the id
     * @return the credit entity
     */
    public CreditEntity addStandardPayment(Long id) {
        CreditEntity creditEntity = getCredit(id);
        try {
            creditEntity.setBalance(BigDecimal.valueOf(
                    creditEntity.getBalance().doubleValue() - creditEntity.getNextPayment().doubleValue()));
            creditEntity.setInstallments(creditEntity.getInstallments() - 1);
            creditEntity.setNextPaymentDate(Date.valueOf(creditEntity.getNextPaymentDate().toLocalDate().plusMonths(1)));

            transactionService.addTransaction(new TransactionRequestDto(
                    creditEntity.getNextPayment(),
                    false,
                    "Loan monthly payment",
                    null,
                    Math.toIntExact(id)));
            return this.creditRepository.save(creditEntity);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Add overpay payment credit entity with specified amount.
     *
     * @param amount the amount
     * @param id     the id
     * @return the credit entity
     */
    public CreditEntity addOverpayPayment(Double amount, Long id) {
        CreditEntity creditEntity = getCredit(id);
        try {
            creditEntity.setBalance(BigDecimal.valueOf(
                    creditEntity.getBalance().doubleValue() - amount));
            if (creditEntity.getBalance().doubleValue() > 0.0) {
                creditEntity.setNextPaymentDate(Date.valueOf(creditEntity.getNextPaymentDate().toLocalDate().plusMonths(1)));
                creditEntity.setNextPayment(BigDecimal.valueOf(creditEntity.getBalance().doubleValue() / creditEntity.getInstallments()));
            } else {
                creditEntity.setInstallments(0);
                creditEntity.setNextPayment(BigDecimal.valueOf(0.0));
                creditEntity.setBalance(BigDecimal.valueOf(0.0));
                creditEntity.setStatus("funded");
            }

            transactionService.addTransaction(new TransactionRequestDto(
                    BigDecimal.valueOf(amount),
                    false,
                    "Loan overpay",
                    null,
                    Math.toIntExact(id)));

            return this.creditRepository.save(creditEntity);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }


}
