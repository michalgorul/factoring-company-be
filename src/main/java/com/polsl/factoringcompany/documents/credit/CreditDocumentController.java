package com.polsl.factoringcompany.documents.credit;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;

/**
 * The type credit document controller. Class for creating endpoints.
 * @author Michal Goral
 * @version 1.0
 */
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/credit/document")
public class CreditDocumentController {

    /**
     * the credit document service bean
     */
    private CreditDocumentService creditDocumentService;

    /**
     * Gets generated credit document in pdf.
     *
     * @param creditNumber the credit number
     * @return the credit pdf
     * @throws Exception the exception
     */
    @GetMapping(path = "/{creditNumber}")
    public ResponseEntity<byte[]> getCreditPdf(@PathVariable String creditNumber) throws Exception {

        byte[] pdf = creditDocumentService.generateDocxFileFromTemplate(creditNumber.replaceAll(",", "/"));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        String filename = "output.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }
}
