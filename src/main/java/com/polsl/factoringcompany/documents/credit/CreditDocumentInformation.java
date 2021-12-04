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

/**
 * The type Credit document information.
 * It stores all variables that are necessary for creating credit document
 * @author Michal Goral
 * @version 1.0
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CreditDocumentInformation {

    /**
     * the loan amount in words
     */
    private String loanAmountInWords;

    /**
     * the loan amount in number
     */
    private String loanAmount;

    /**
     * the date of creation credit entity
     */
    private String creationDate;

    /**
     * the username
     */
    private String userName;

    /**
     * the user's mail address
     */
    private String userEmail;

    /**
     * the user's city
     */
    private String userCity;

    /**
     * the date of next installment payment
     */
    private String nextInstallmentDate;

    /**
     * the day of month that user is obliged to pay installment
     */
    private String paymentDay;

    /**
     * the last installment date
     */
    private String lastInstallmentDate;

    /**
     * the credit interest of factoring company
     */
    private String interestInWords;

    /**
     * the rate of interest of factoring company
     */
    private String rateOfInterest;

    /**
     * The constant ApplicationContext .
     */
    public static ApplicationContext ctx;

    /**
     * Instantiates a new Credit document information.
     *
     * @param creditEntity the credit entity
     * @param userEntity   the user entity
     */
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

    /**
     * Gets all variables of this instance in hash map.
     *
     * @return the variables in hash map
     */
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
