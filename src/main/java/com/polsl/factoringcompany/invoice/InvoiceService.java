package com.polsl.factoringcompany.invoice;

import com.polsl.factoringcompany.currency.CurrencyEntity;
import com.polsl.factoringcompany.currency.CurrencyService;
import com.polsl.factoringcompany.customer.CustomerEntity;
import com.polsl.factoringcompany.customer.CustomerService;
import com.polsl.factoringcompany.exceptions.IdNotFoundInDatabaseException;
import com.polsl.factoringcompany.invoiceitem.InvoiceItemDto;
import com.polsl.factoringcompany.invoiceitem.InvoiceItemService;
import com.polsl.factoringcompany.paymenttype.PaymentTypeEntity;
import com.polsl.factoringcompany.paymenttype.PaymentTypeService;
import com.polsl.factoringcompany.product.ProductEntity;
import com.polsl.factoringcompany.product.ProductService;
import com.polsl.factoringcompany.transaction.TransactionRequestDto;
import com.polsl.factoringcompany.transaction.TransactionService;
import com.polsl.factoringcompany.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.allegro.finance.tradukisto.MoneyConverters;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Formatter;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final UserService userService;
    private final CurrencyService currencyService;
    private final PaymentTypeService paymentTypeService;
    private final CustomerService customerService;
    private final ProductService productService;
    private final InvoiceItemService invoiceItemService;
    private final TransactionService transactionService;

    public List<InvoiceEntity> getInvoices() {
        return this.invoiceRepository.findAll();
    }


    public List<InvoiceEntity> getInvoicesCurrentUser() {
        Long currentUserId = userService.getCurrentUserId();
        return this.invoiceRepository.findAllByUserId(Math.toIntExact(currentUserId));
    }

    public InvoiceEntity getInvoice(Long id) {
        return this.invoiceRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundInDatabaseException("Invoice", id));
    }


    public InvoiceEntity addInvoice(InvoiceCreateRequest invoiceCreateRequest) {

        try {
            String newInvoiceNumber = getNewInvoiceNumber(invoiceCreateRequest);
            CurrencyEntity currency = currencyService.getCurrencyByCurrencyName(invoiceCreateRequest.getCurrencyName());
            PaymentTypeEntity paymentType = paymentTypeService.getPaymentTypeEntityByName(invoiceCreateRequest.getPaymentTypeName());
            CustomerEntity customer = customerService.getCustomerByPhone(invoiceCreateRequest.getCustomerPhone());
            ProductEntity product = productService.getProductByName(invoiceCreateRequest.getProductName());
            Long currentUserId = userService.getCurrentUserId();

            InvoiceDto invoiceDto = new InvoiceDto(
                    invoiceCreateRequest,
                    newInvoiceNumber,
                    customer.getId(),
                    paymentType.getId(),
                    currency.getId(),
                    currentUserId);


            InvoiceEntity invoiceEntity = this.invoiceRepository.save(new InvoiceEntity(invoiceDto));

            InvoiceItemDto invoiceItemDto = new InvoiceItemDto(invoiceCreateRequest, product.getId(), invoiceEntity.getId());

            invoiceItemService.addInvoiceItem(invoiceItemDto);

            transactionService.addTransaction(new TransactionRequestDto(
                    invoiceEntity.getToPay(),
                    true,
                    "Receiving money for an invoice",
                    Math.toIntExact(invoiceEntity.getId()),
                    null));
            return invoiceEntity;


        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public InvoiceEntity updateInvoice(Long id, InvoiceDto invoiceDto) {

        Optional<InvoiceEntity> invoiceEntityOptional = invoiceRepository.findById(id);

        if (invoiceEntityOptional.isEmpty())
            throw new IdNotFoundInDatabaseException("Invoice", id);


        try {
            invoiceEntityOptional.get().setInvoiceNumber(invoiceEntityOptional.get().getInvoiceNumber());
            invoiceEntityOptional.get().setCreationDate(invoiceEntityOptional.get().getCreationDate());
            invoiceEntityOptional.get().setSaleDate(invoiceDto.getSaleDate());
            invoiceEntityOptional.get().setPaymentDeadline(invoiceDto.getPaymentDeadline());
            invoiceEntityOptional.get().setToPay(invoiceDto.getToPay());

            MoneyConverters converter = MoneyConverters.ENGLISH_BANKING_MONEY_VALUE;
            String toPayInWords = converter.asWords(invoiceDto.getToPay());

            invoiceEntityOptional.get().setToPayInWords(toPayInWords);
            invoiceEntityOptional.get().setPaidByUser(invoiceDto.getPaidByUser());
            invoiceEntityOptional.get().setToPayByUser(BigDecimal.valueOf(invoiceDto.getToPay().doubleValue() -
                    invoiceDto.getPaidByUser().doubleValue()));
            invoiceEntityOptional.get().setRemarks(invoiceDto.getRemarks());
            invoiceEntityOptional.get().setStatus(invoiceDto.getStatus());
            invoiceEntityOptional.get().setCurrencyId(invoiceDto.getCurrencyId());
            invoiceEntityOptional.get().setPaymentTypeId(invoiceDto.getPaymentTypeId());

            return this.invoiceRepository.save(invoiceEntityOptional.get());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteInvoice(Long id) {
        try {
            this.invoiceRepository.deleteById(id);
        } catch (RuntimeException ex) {
            throw new IdNotFoundInDatabaseException("Invoice", id);
        }
    }

    public String getInvoiceCurrencyCode(Long invoiceId) {
        InvoiceEntity invoiceEntity = this.getInvoice(invoiceId);
        return currencyService.getCurrency((long) invoiceEntity.getCurrencyId()).getCode();

    }

    public String getPaymentMethod(Long invoiceId) {
        InvoiceEntity invoiceEntity = this.getInvoice(invoiceId);
        return paymentTypeService.getPaymentType((long) invoiceEntity.getPaymentTypeId()).getPaymentTypeName();
    }

    private String getNewInvoiceNumber(InvoiceCreateRequest invoiceCreateRequest) {
        StringBuilder newInvoiceNumber = new StringBuilder();
        Formatter formatter = new Formatter(newInvoiceNumber);
        int month = invoiceCreateRequest.getIssueDate().toLocalDateTime().getMonthValue();
        int year = invoiceCreateRequest.getIssueDate().toLocalDateTime().getYear();
        long lastInvoiceIdPlusOne = 1L;

        try {
            lastInvoiceIdPlusOne =
                    invoiceRepository.getInvoiceNumber(month, year) + 1;
        } catch (NullPointerException ignored) {
        }
        formatter.format("%d/%d/%d", lastInvoiceIdPlusOne, month, year);

        return newInvoiceNumber.toString();
    }

    public InvoiceEntity updateInvoicePaymentInfo(Long id, InvoicePaymentInfoUpdateRequest invoicePaymentInfoUpdateRequest) {

        Optional<InvoiceEntity> invoiceEntityOptional = invoiceRepository.findById(id);

        if (invoiceEntityOptional.isEmpty())
            throw new IdNotFoundInDatabaseException("Invoice", id);

        try {
            CurrencyEntity currencyEntity =
                    currencyService.getCurrencyByCurrencyName(invoicePaymentInfoUpdateRequest.getCurrencyName());
            PaymentTypeEntity paymentTypeEntity =
                    paymentTypeService.getPaymentTypeEntityByName(invoicePaymentInfoUpdateRequest.getPaymentTypeName());

            invoiceEntityOptional.get().setCurrencyId(Math.toIntExact(currencyEntity.getId()));
            invoiceEntityOptional.get().setPaymentTypeId(Math.toIntExact(paymentTypeEntity.getId()));


            return this.invoiceRepository.save(invoiceEntityOptional.get());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    public List<String> getInvoiceStatuses() {
        return this.invoiceRepository.getStatuses();

    }

    public Double getActiveInvoicesPaidValue() {

        Long currentUserId = userService.getCurrentUserId();

        List<InvoiceEntity> allByUserId = this.invoiceRepository.findAllByUserId(Math.toIntExact(currentUserId));

        return allByUserId
                .stream()
                .map(InvoiceEntity::getPaidByUser)
                .mapToDouble(BigDecimal::doubleValue).sum();

    }

    private void updateFromActiveToUnfunded(InvoiceEntity invoiceEntity) {
        try {
            if (invoiceEntity.getPaymentDeadline().before(Timestamp.valueOf(LocalDateTime.now())) &&
                    invoiceEntity.getStatus().equals("active")) {
                invoiceEntity.setStatus("unfunded");
                this.invoiceRepository.save(invoiceEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    @Scheduled(cron = "[Seconds] [Minutes] [Hours] [Day of month] [Month] [Day of week] [Year]")
    //    Fires at 12 PM every day:
    @Scheduled(cron = "0 1 0 * * ?")
    public void updateInvoiceStatuses() {
        List<InvoiceEntity> allByStatusEqualsActive = this.invoiceRepository.findAllByStatusEquals("active");
        for (InvoiceEntity invoiceEntity : allByStatusEqualsActive) {
            updateFromActiveToUnfunded(invoiceEntity);
        }
        System.out.println("invoice statuses updated");
    }

    public void payForInvoice(Long id) {
        try {
            InvoiceEntity invoiceEntity = this.invoiceRepository.findById(id).orElse(null);
            if (invoiceEntity != null) {
                invoiceEntity.setStatus("funded");
                invoiceEntity.setPaidByUser(invoiceEntity.getToPayByUser());
                invoiceEntity.setToPayByUser(BigDecimal.ZERO);

                transactionService.addTransaction(new TransactionRequestDto(
                        invoiceEntity.getPaidByUser(),
                        false,
                        "Invoice payment",
                        Math.toIntExact(invoiceEntity.getId()),
                        null));
                this.invoiceRepository.save(invoiceEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
