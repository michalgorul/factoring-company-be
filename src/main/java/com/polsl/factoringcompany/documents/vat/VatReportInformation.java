package com.polsl.factoringcompany.documents.vat;

import com.polsl.factoringcompany.bankaccount.BankAccountEntity;
import com.polsl.factoringcompany.company.CompanyEntity;
import com.polsl.factoringcompany.customer.CustomerEntity;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The type vat report document information.
 * It stores all variables that are necessary for creating invoice document
 *
 * @author Michal Goral
 * @version 1.0
 */
@EqualsAndHashCode
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class VatReportInformation {

    /**
     * the name of company from vat white list
     */
    private String name;

    /**
     * the nip number of company from vat white list
     * <a href="https://pl.wikipedia.org/wiki/Numer_identyfikacji_podatkowej">See more</a>
     */
    private String nip;

    /**
     * the status of vat of company from vat white list
     */
    private String statusVat;

    /**
     * the regon number of company from vat white list
     * <a href="https://pl.wikipedia.org/wiki/REGON">See more</a>
     */
    private String regon;

    /**
     * the pesel number associated with company from vat white list
     */
    private String pesel;

    /**
     * the krs number of company from vat white list
     */
    private String krs;

    /**
     * the residence address of company from vat white list
     */
    private String residenceAddress;

    /**
     * the working address of company from vat white list
     */
    private String workingAddress;

    /**
     * the representatives of company from vat white in list
     */
    private List<String> representatives;

    /**
     * the authorized clerks of company from vat white in list
     */
    private List<String> authorizedClerks;

    /**
     * the partners of company from vat white in list
     */
    private List<String> partners;

    /**
     * the date of registration of company from vat white list
     */
    private String registrationLegalDate;

    /**
     * the registration denial basis of company from vat white list
     */
    private Object registrationDenialBasis;

    /**
     * the date of registration denial of company from vat white list
     */
    private Object registrationDenialDate;

    /**
     * the restoration basis of company from vat white list
     */
    private Object restorationBasis;

    /**
     * the date of restoration of company from vat white list
     */
    private Object restorationDate;

    /**
     * the removal basis of company from vat white list
     */
    private Object removalBasis;

    /**
     * the removal date of company from vat white list
     */
    private Object removalDate;

    /**
     * the bank account numbers of company from vat white list
     */
    private List<String> accountNumbers;

    /**
     * the information if company has virtual accounts from vat white list
     */
    private boolean hasVirtualAccounts;

    /**
     * the customer name
     */
    private String customerName;

    /**
     * the customer email address
     */
    private String customerEmail;

    /**
     * the customer country
     */
    private String customerCountry;

    /**
     * the customer city
     */
    private String customerCity;

    /**
     * the customer street
     */
    private String customerStreet;

    /**
     * the customer postal code
     */
    private String customerPostalCode;

    /**
     * the customer phone number
     */
    private String customerPhone;

    /**
     * the customer company name
     */
    private String customerCompanyName;

    /**
     * the customer company country
     */
    private String customerCompanyCountry;

    /**
     * the customer company city
     */
    private String customerCompanyCity;

    /**
     * the customer company street
     */
    private String customerCompanyStreet;

    /**
     * the customer company postal code
     */
    private String customerCompanyPostalCode;

    /**
     * the customer company nip number
     * <a href="https://pl.wikipedia.org/wiki/Numer_identyfikacji_podatkowej">See more</a>
     */
    private String customerCompanyNip;

    /**
     * the customer company regon number
     * <a href="https://pl.wikipedia.org/wiki/REGON">See more</a>
     */
    private String customerCompanyRegon;

    /**
     * the customer company bank account number
     */
    private String customerCompanyBankAccount;


    /**
     * Instantiates a new Vat report information.
     *
     * @param vatInformationInMap the vat information in map
     * @param customerEntity      the customer entity
     * @param companyEntity       the company entity
     * @param bankAccountEntity   the bank account entity
     */
    VatReportInformation(HashMap<String, Object> vatInformationInMap, CustomerEntity customerEntity,
                         CompanyEntity companyEntity, BankAccountEntity bankAccountEntity) {

        List<Object> tempRepresentatives;
        List<Object> tempAuthorizedClerks;
        List<Object> tempPartners;
        List<Object> tempAccountNumbers;


        this.name = (String) vatInformationInMap.get("name");
        this.nip = (String) vatInformationInMap.get("nip");
        this.statusVat = (String) vatInformationInMap.get("statusVat");
        this.regon = (String) vatInformationInMap.get("regon");
        this.pesel = (String) vatInformationInMap.get("pesel");
        this.krs = (String) vatInformationInMap.get("krs");
        this.residenceAddress = (String) vatInformationInMap.get("residenceAddress");
        this.workingAddress = (String) vatInformationInMap.get("workingAddress");

        tempRepresentatives = (List<Object>) Collections.singletonList(vatInformationInMap.get("tempRepresentatives")).get(0);
        if (tempRepresentatives != null)
            this.representatives = tempRepresentatives.stream().map(object -> Objects.toString(object, null))
                    .collect(Collectors.toList());
        else
            this.representatives = null;

        tempAuthorizedClerks = (List<Object>) Collections.singletonList(vatInformationInMap.get("tempAuthorizedClerks")).get(0);
        if (tempAuthorizedClerks != null)
            this.authorizedClerks = tempAuthorizedClerks.stream().map(object -> Objects.toString(object, null))
                    .collect(Collectors.toList());
        else
            this.authorizedClerks = null;

        tempPartners = (List<Object>) Collections.singletonList(vatInformationInMap.get("partners")).get(0);
        if (tempPartners != null)
            this.partners = tempPartners.stream().map(object -> Objects.toString(object, null))
                    .collect(Collectors.toList());
        else
            this.partners = null;

        this.registrationLegalDate = (String) vatInformationInMap.get("registrationLegalDate");
        this.registrationDenialBasis = vatInformationInMap.get("registrationDenialBasis");
        this.registrationDenialDate = vatInformationInMap.get("registrationDenialDate");
        this.restorationBasis = vatInformationInMap.get("restorationBasis");
        this.restorationDate = vatInformationInMap.get("restorationDate");
        this.removalBasis = vatInformationInMap.get("removalBasis");
        this.removalDate = vatInformationInMap.get("removalDate");

        tempAccountNumbers = (List<Object>) Collections.singletonList(vatInformationInMap.get("accountNumbers")).get(0);
        if (tempAccountNumbers != null)
            this.accountNumbers = tempAccountNumbers.stream().map(object -> Objects.toString(object, null))
                    .collect(Collectors.toList());
        else
            this.accountNumbers = null;


        this.hasVirtualAccounts = (boolean) vatInformationInMap.get("hasVirtualAccounts");

        this.customerName = customerEntity.getFirstName() + " " + customerEntity.getLastName();
        this.customerEmail = customerEntity.getEmail();
        this.customerCountry = customerEntity.getCountry();
        this.customerCity = customerEntity.getCity();
        this.customerStreet = customerEntity.getStreet();
        this.customerPostalCode = customerEntity.getPostalCode();
        this.customerPhone = customerEntity.getPhone();

        this.customerCompanyName = companyEntity.getCompanyName();
        this.customerCompanyCountry = companyEntity.getCountry();
        this.customerCompanyCity = companyEntity.getCity();
        this.customerCompanyStreet = companyEntity.getStreet();
        this.customerCompanyPostalCode = companyEntity.getPostalCode();
        this.customerCompanyNip = companyEntity.getNip();
        this.customerCompanyRegon = companyEntity.getRegon();
        this.customerCompanyBankAccount = bankAccountEntity.getBankAccountNumber();
    }

    /**
     * Gets all variables of this instance in hash map.
     *
     * @return the variables in hash map
     */
    public HashMap<String, String> getVariablesInHashMap() {
        HashMap<String, String> variables = new HashMap<>();

        variables.put("name", getNullString(this.name));
        variables.put("nip", getNullString(this.nip));
        variables.put("statusVat", getNullString(this.statusVat));
        variables.put("regon", getNullString(this.regon));
        variables.put("pesel", getNullString(this.pesel));
        variables.put("krs", getNullString(this.krs));
        variables.put("residenceAddress", getNullString(this.residenceAddress));
        variables.put("workingAddress", getNullString(this.workingAddress));
        variables.put("representatives", getListInString(this.representatives));
        variables.put("authorizedClerks", getListInString(this.authorizedClerks));
        variables.put("partners", getListInString(this.partners));
        variables.put("registrationLegalDate", this.registrationLegalDate);
        variables.put("registrationDenialBasis", this.getObjectToString(this.registrationDenialBasis));
        variables.put("registrationDenialDate", this.getObjectToString(this.registrationDenialDate));
        variables.put("restorationBasis", this.getObjectToString(this.restorationBasis));
        variables.put("restorationDate", this.getObjectToString(this.restorationDate));
        variables.put("removalBasis", this.getObjectToString(this.removalBasis));
        variables.put("removalDate", this.getObjectToString(this.removalDate));
        variables.put("accountNumbers", this.getListInString(this.accountNumbers));
        variables.put("hasVirtualAccounts", this.hasVirtualAccountsToString());
        variables.put("customerName", this.customerName);
        variables.put("customerEmail", this.customerEmail);
        variables.put("customerCountry", this.customerCountry);
        variables.put("customerCity", this.customerCity);
        variables.put("customerStreet", this.customerStreet);
        variables.put("customerPostalCode", this.customerPostalCode);
        variables.put("customerPhone", this.customerPhone);
        variables.put("customerCompanyName", this.customerCompanyName);
        variables.put("customerCompanyCountry", this.customerCompanyCountry);
        variables.put("customerCompanyCity", this.customerCompanyCity);
        variables.put("customerCompanyStreet", this.customerCompanyStreet);
        variables.put("customerCompanyPostalCode", this.customerCompanyPostalCode);
        variables.put("customerCompanyNip", this.customerCompanyNip);
        variables.put("customerCompanyRegon", this.customerCompanyRegon);
        variables.put("customerCompanyBankAccount", this.customerCompanyBankAccount);

        return variables;
    }

    /**
     * Handles converting list to string
     * @param list the list
     * @return the string
     */
    private String getListInString(List<String> list) {
        return list == null || list.isEmpty() ? " - " : StringUtils.join(list, ", ");
    }

    /**
     * Handles conversion hasVirtualAccounts field to string
     * @return the hasVirtualAccounts in string
     */
    private String hasVirtualAccountsToString() {
        return this.hasVirtualAccounts ? "true" : "false";
    }

    /**
     * Handles conversion of object to string
     * @param o the object
     * @return the string
     */
    private String getObjectToString(Object o) {
        return o != null ? o.toString() : " - ";
    }

    /**
     * Handles situation in which string is null
     * @param s the string
     * @return the converted string
     */
    private String getNullString(String s) {
        return s == null ? " - " : s;
    }


}

