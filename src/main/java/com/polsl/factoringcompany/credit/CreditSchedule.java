package com.polsl.factoringcompany.credit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class CreditSchedule {
    private Integer id;
    private Date dateTime;
    private String description;
    private Double amount;
    private Double balance;

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
