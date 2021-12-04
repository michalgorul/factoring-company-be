package com.polsl.factoringcompany.documents.invoice;

import com.polsl.factoringcompany.company.CompanyEntity;
import com.polsl.factoringcompany.customer.CustomerEntity;
import com.polsl.factoringcompany.invoice.InvoiceEntity;
import com.polsl.factoringcompany.invoiceitem.InvoiceItemEntity;
import com.polsl.factoringcompany.product.ProductEntity;
import com.polsl.factoringcompany.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;


/**
 * The type Invoice document information.
 * It stores all variables that are necessary for creating invoice document
 * @author Michal Goral
 * @version 1.0
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class InvoiceInformation {

    /**
     * the invoice number
     */
    private String invoiceNumber;

    /**
     * the date of delivery date on invoice
     */
    private String deliveryDate;

    /**
     * the date of issue on invoice
     */
    private String issueDate;

    /**
     * the customer name
     */
    private String customerName;

    /**
     * the customer street
     */
    private String customerStreet;

    /**
     * the customer city
     */
    private String customerCity;

    /**
     * the delivery of service method
     */
    private String deliveryMethod;

    /**
     * the description of delivery
     */
    private String deliveryDescription;

    /**
     * the payment method
     */
    private String paymentMethod;

    /**
     * the item on invoice name
     */
    private String itemName;

    /**
     * the PKWIU of product on invoice
     */
    private String pkwiu;

    /**
     * the quantity of product
     */
    private String quantity;

    /**
     * the measure unit of product
     */
    private String unit;

    /**
     * the vat rate of product
     */
    private String vat;

    /**
     * the gross value on invoice
     */
    private String gross;

    /**
     * the net value on invoice
     */
    private String net;

    /**
     * the vat value for whole invoice
     */
    private String vatValue;

    /**
     * the amount to pay on invoice
     */
    private String toPay;

    /**
     * the amount to pay on invoice in words
     */
    private String toPayInWords;

    /**
     * the seller company name
     */
    private String sellerCompany;

    /**
     * the seller street
     */
    private String sellerStreet;

    /**
     * the seller postal code
     */
    private String sellerPostalCode;

    /**
     * the seller city
     */
    private String sellerCity;

    /**
     * the seller country
     */
    private String sellerCountry;

    /**
     * the seller phone number
     */
    private String sellerPhone;

    /**
     * the seller company's NIP number
     * <a href="https://pl.wikipedia.org/wiki/Numer_identyfikacji_podatkowej">See more</a>
     */
    private String sellerNip;

    /**
     * the seller company's REGON number
     * <a href="https://pl.wikipedia.org/wiki/REGON">See more</a>
     */
    private String sellerRegon;

    /**
     * The constant ApplicationContext .
     */
    public static ApplicationContext ctx;

    /**
     * Instantiates a new Invoice information.
     *
     * @param invoiceEntity     the invoice entity
     * @param customerEntity    the customer entity
     * @param invoiceItemEntity the invoice item entity
     * @param productEntity     the product entity
     * @param currencyCode      the currency code
     * @param paymentMethod     the payment method
     * @param userEntity        the user entity
     * @param companyEntity     the company entity
     */
    public InvoiceInformation(InvoiceEntity invoiceEntity, CustomerEntity customerEntity,
                              InvoiceItemEntity invoiceItemEntity, ProductEntity productEntity,
                              String currencyCode, String paymentMethod, UserEntity userEntity, CompanyEntity companyEntity) {

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String deliveryDateFormat = df.format(invoiceEntity.getSaleDate());
        String issueDateFormat = df.format(invoiceEntity.getCreationDate());

        this.invoiceNumber = invoiceEntity.getInvoiceNumber();
        this.deliveryDate = deliveryDateFormat;
        this.issueDate = issueDateFormat;
        this.customerName = String.format("%s %s", customerEntity.getFirstName(), customerEntity.getLastName());
        this.customerStreet = String.format("%s", customerEntity.getStreet());
        this.customerCity = String.format("%s %s %s", customerEntity.getCity(), customerEntity.getPostalCode(),
                customerEntity.getCountry());

        this.deliveryMethod = "UPS";
        this.deliveryDescription = "lorem ipsum";

        this.paymentMethod = paymentMethod;
        this.itemName = productEntity.getName();
        this.pkwiu = productEntity.getPkwiu();
        this.quantity = String.valueOf(invoiceItemEntity.getQuantity());
        this.unit = productEntity.getMeasureUnit();

        DecimalFormat decimalFormat = new DecimalFormat("#.##");

        this.vat = decimalFormat.format(invoiceItemEntity.getVatRate().doubleValue() * 100) + "%";
        this.gross = String.valueOf(invoiceItemEntity.getGrossValue());
        this.net = String.valueOf(invoiceItemEntity.getNetValue());
        this.vatValue = String.valueOf(invoiceItemEntity.getVatValue());
        this.toPay = invoiceEntity.getToPay() + " " + currencyCode;
        this.toPayInWords = invoiceEntity.getToPayInWords() + " " + currencyCode;
        this.sellerCompany = companyEntity.getCompanyName();
        this.sellerStreet = companyEntity.getStreet();
        this.sellerPostalCode = companyEntity.getPostalCode();
        this.sellerCity = companyEntity.getCity();
        this.sellerCountry = companyEntity.getCountry();
        this.sellerPhone = userEntity.getPhone();
        this.sellerNip = companyEntity.getNip();
        this.sellerRegon = companyEntity.getRegon();
    }

    /**
     * Gets all variables of this instance in hash map.
     *
     * @return the variables in hash map
     */
    public HashMap<String, String> getVariablesInHashMap() {
        HashMap<String, String> variables = new HashMap<>();
        variables.put("invoice_number", this.invoiceNumber);
        variables.put("delivery_date", this.deliveryDate);
        variables.put("issue_date", this.issueDate);
        variables.put("customer_name", this.customerName);
        variables.put("customer_street", this.customerStreet);
        variables.put("customer_city", this.customerCity);
        variables.put("delivery_method", this.deliveryMethod);
        variables.put("delivery_description", this.deliveryDescription);
        variables.put("payment_method", this.paymentMethod);
        variables.put("item_name", this.itemName);
        variables.put("pkwiu", this.pkwiu);
        variables.put("qnt", this.quantity);
        variables.put("unit", this.unit);
        variables.put("vat", this.vat);
        variables.put("gross", this.gross);
        variables.put("net", this.net);
        variables.put("vat_value", this.vatValue);
        variables.put("to_pay", this.toPay);
        variables.put("to_pay_in_words", this.toPayInWords);
        variables.put("seler_company", this.sellerCompany);
        variables.put("seler_street", this.sellerStreet);
        variables.put("seler_postal_code", this.sellerPostalCode);
        variables.put("seler_city", this.sellerCity);
        variables.put("seler_country", this.sellerCountry);
        variables.put("seler_phone", this.sellerPhone);
        variables.put("seler_nip", this.sellerNip);
        variables.put("seler_regon", this.sellerRegon);

        return variables;
    }
}
