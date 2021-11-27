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

@EqualsAndHashCode
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class VatReportInformation {

    private String name;
    private String nip;
    private String statusVat;
    private String regon;
    private String pesel;
    private String krs;
    private String residenceAddress;
    private String workingAddress;
    private List<String> representatives;
    private List<String> authorizedClerks;
    private List<String> partners;
    private String registrationLegalDate;
    private Object registrationDenialBasis;
    private Object registrationDenialDate;
    private Object restorationBasis;
    private Object restorationDate;
    private Object removalBasis;
    private Object removalDate;
    private List<String> accountNumbers;
    private boolean hasVirtualAccounts;

    private String customerName;
    private String customerEmail;
    private String customerCountry;
    private String customerCity;
    private String customerStreet;
    private String customerPostalCode;
    private String customerPhone;

    private String customerCompanyName;
    private String customerCompanyCountry;
    private String customerCompanyCity;
    private String customerCompanyStreet;
    private String customerCompanyPostalCode;
    private String customerCompanyNip;
    private String customerCompanyRegon;
    private String customerCompanyBankAccount;


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

    private String getListInString(List<String> list) {
        return list == null || list.isEmpty() ? " - " : StringUtils.join(list, ", ");
    }

    private String hasVirtualAccountsToString() {
        return this.hasVirtualAccounts ? "true" : "false";
    }

    private String getObjectToString(Object o) {
        return o != null ? o.toString() : " - ";
    }

    private String getNullString(String s) {
        return s == null ? " - " : s;
    }


}

