package com.polsl.factoringcompany.documents.credit;

import com.polsl.factoringcompany.credit.CreditEntity;
import com.polsl.factoringcompany.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import pl.allegro.finance.tradukisto.MoneyConverters;

import java.util.HashMap;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CreditDocumentInformation {

    private String loanAmountInWords;
    private String loanAmount;
    private String creationDate;
    private String userName;
    private String userEmail;
    private String userCity;
    private String nextInstallmentDate;
    private String paymentDay;
    private String lastInstallmentDate;
    private String interestInWords;
    private String rateOfInterest;

    public static ApplicationContext ctx;

    public CreditDocumentInformation(CreditEntity creditEntity, UserEntity userEntity) {
        MoneyConverters converter = MoneyConverters.ENGLISH_BANKING_MONEY_VALUE;
        String loanAmountInWords = converter.asWords(creditEntity.getAmount());
        String interestInWords = converter.asWords(creditEntity.getRateOfInterest());

        this.loanAmountInWords = loanAmountInWords.replaceAll(" £", "");
        this.loanAmount = creditEntity.getAmount().toString();
        this.creationDate = creditEntity.getCreationDate().toString();
        this.userName = userEntity.getFirstName() + ' ' + userEntity.getLastName();
        this.userEmail = userEntity.getEmail();
        this.userCity = userEntity.getCity();
        this.nextInstallmentDate = creditEntity.getNextPaymentDate().toString();
        this.paymentDay = String.valueOf(creditEntity.getPaymentDay());
        this.lastInstallmentDate = creditEntity.getLastInstallmentDate().toString();
        this.interestInWords = interestInWords.replaceAll(" £", "");
        this.rateOfInterest = creditEntity.getRateOfInterest().toString();

    }

    public HashMap<String, String> getVariablesInHashMap() {
        HashMap<String, String> variables = new HashMap<>();
        variables.put("loanAmountInWords", this.loanAmountInWords);
        variables.put("loanAmount", this.loanAmount);
        variables.put("creationDate", this.creationDate);
        variables.put("userName", this.userName);
        variables.put("userEmail", this.userEmail);
        variables.put("userCity", this.userCity);
        variables.put("nextInstallmentDate", this.nextInstallmentDate);
        variables.put("paymentDay", this.paymentDay);
        variables.put("lastInstallmentDate", this.lastInstallmentDate);
        variables.put("interestInWords", this.interestInWords);
        variables.put("rateOfInterest", this.rateOfInterest);

        return variables;
    }
}
