package com.polsl.factoringcompany.documents.credit;

import com.polsl.factoringcompany.credit.CreditEntity;
import com.polsl.factoringcompany.credit.CreditService;
import com.polsl.factoringcompany.user.UserService;
import lombok.AllArgsConstructor;
import org.docx4j.Docx4J;
import org.docx4j.model.datastorage.migration.VariablePrepare;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

@Service
@AllArgsConstructor
public class CreditDocumentService {
    private final static String PATH = "src/main/resources/templates/loan_agreement_template.docx";
    private final UserService userService;
    private final CreditService creditService;

    public byte[] generateDocxFileFromTemplate(String creditNumber) throws Exception {

        File invoiceTemplate = new File(PATH);
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(invoiceTemplate);
        MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
        VariablePrepare.prepare(wordMLPackage);

        CreditEntity creditEntity = creditService.getCreditBuNumber(creditNumber);

        CreditDocumentInformation creditDocumentInformation = new CreditDocumentInformation(
                creditEntity, userService.getCurrentUser());


        HashMap<String, String> variables = creditDocumentInformation.getVariablesInHashMap();

        documentPart.variableReplace(variables);
        FileOutputStream os = new FileOutputStream("src/main/resources/static/temp.pdf");
        Docx4J.toPDF(wordMLPackage, os);
        Path pdfPath = Paths.get("src/main/resources/static/temp.pdf");
        byte[] pdf = Files.readAllBytes(pdfPath);
        os.flush();
        os.close();

        return pdf;

    }
}
