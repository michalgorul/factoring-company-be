package com.polsl.factoringcompany.documents.vat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.polsl.factoringcompany.bankaccount.BankAccountEntity;
import com.polsl.factoringcompany.bankaccount.BankAccountService;
import com.polsl.factoringcompany.company.CompanyEntity;
import com.polsl.factoringcompany.company.CompanyService;
import com.polsl.factoringcompany.customer.CustomerEntity;
import com.polsl.factoringcompany.customer.CustomerService;
import lombok.AllArgsConstructor;
import org.docx4j.Docx4J;
import org.docx4j.model.datastorage.migration.VariablePrepare;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@Service
@AllArgsConstructor
public class VatReportService {

    private final static String PATH = "src/main/resources/templates/vat_report_template.docx";
    private final CustomerService customerService;
    private final CompanyService companyService;
    private RestTemplate restTemplate;
    private final BankAccountService bankAccountService;


    public byte[] generateDocxFileFromTemplate(Long customerId) throws Exception {

        File invoiceTemplate = new File(PATH);
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(invoiceTemplate);
        MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
        VariablePrepare.prepare(wordMLPackage);

        VatReportInformation vatReportInformation = this.getCustomerVatInformation(customerId);

        HashMap<String, String> variables = vatReportInformation.getVariablesInHashMap();

        documentPart.variableReplace(variables);
        FileOutputStream os = new FileOutputStream("src/main/resources/static/temp.pdf");
        Docx4J.toPDF(wordMLPackage, os);
        Path pdfPath = Paths.get("src/main/resources/static/temp.pdf");
        byte[] pdf = Files.readAllBytes(pdfPath);
        os.flush();
        os.close();

        return pdf;
    }

    public VatReportInformation getCustomerVatInformation(Long customerId) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedNow = now.format(dateTimeFormatter);

        CustomerEntity customer = customerService.getCustomer(customerId);
        CompanyEntity company = companyService.getCompany(Long.valueOf(customer.getCompanyId()));
        BankAccountEntity bankAccount = bankAccountService.getCustomersBankAccount(customer);
        String url = "https://wl-api.mf.gov.pl/api/search/nip/" + company.getNip() + "?date=" + formattedNow;

        String vat = restTemplate.getForObject(url, String.class);
        assert vat != null;
        vat = vat.replace("{\"result\":{\"subject\":", "");
        vat = vat.replaceAll(",\"requestId\":\".*\",\"requestDateTime\":\".*\"}}", "");

        HashMap<String, Object> map = new Gson().fromJson(vat, new TypeToken<HashMap<String, Object>>() {
        }.getType());

        return new VatReportInformation(map, customer, company, bankAccount);
    }

}
