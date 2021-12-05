package com.polsl.factoringcompany.credit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Credit schedule.
 * @author Michal Goral
 * @version 1.0
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class CreditSchedule {

    /**
     * the id
     */
    private Integer id;

    /**
     * the date
     */
    private Date dateTime;

    /**
     * the description
     */
    private String description;

    /**
     * the amount
     */
    private Double amount;

    /**
     * the balance
     */
    private Double balance;

    /**
     * Gets schedule of specific credit.
     *
     * @param creditEntity the credit entity
     * @return the schedule
     */
    public static List<CreditSchedule> getSchedule(CreditEntity creditEntity) {
        List<CreditSchedule> creditScheduleList = new ArrayList<>();
        double currentBalance = creditEntity.getAmount().doubleValue() - creditEntity.getNextPayment().doubleValue();
        CreditSchedule creditScheduleFirst = new CreditSchedule(
                1,
                creditEntity.getNextPaymentDate(),
                "payment",
                creditEntity.getNextPayment().doubleValue(),
                currentBalance);
        creditScheduleList.add(creditScheduleFirst);

        for (int i = 0; i < creditEntity.getInstallments() - 1; i++) {
            currentBalance -= creditEntity.getNextPayment().doubleValue();
            CreditSchedule creditSchedule = new CreditSchedule(
                    i + 2,
                    Date.valueOf(creditEntity.getNextPaymentDate().toLocalDate().plusMonths(i + 1)),
                    "payment",
                    creditEntity.getNextPayment().doubleValue(),
                    currentBalance);
            creditScheduleList.add(creditSchedule);
        }
        return creditScheduleList;
    }
}
