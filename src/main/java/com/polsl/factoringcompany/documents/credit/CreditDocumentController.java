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

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/credit/document")
public class CreditDocumentController {

    private CreditDocumentService creditDocumentService;

    @GetMapping(path = "/{creditNumber}")
    public ResponseEntity<byte[]> getCreditPdf(@PathVariable String creditNumber) throws Exception {

        byte[] pdf = creditDocumentService.generateDocxFileFromTemplate(creditNumber.replaceAll(",", "/"));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        // Here you have to set the actual filename of your pdf
        String filename = "output.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }
}
