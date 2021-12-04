package com.polsl.factoringcompany.documents.invoice;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Invoice pdf controller. Class for creating endpoints.
 * @author Michal Goral
 * @version 1.0
 */
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/invoice/pdf")
public class InvoicePdfController {

    /**
     * the invoice pdf service bean
     */
    private InvoicePdfService invoicePdfService;

    /**
     * Gets generated invoice document in pdf.
     *
     * @param invoiceId the invoice id
     * @return the invoice pdf in byte array
     * @throws Exception the exception
     */
    @GetMapping(path = "/{invoiceId}")
    public ResponseEntity<byte[]> getInvoicePdf(@PathVariable Long invoiceId) throws Exception {

        byte[] pdf = invoicePdfService.generateDocxFileFromTemplate(invoiceId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        // Here you have to set the actual filename of your pdf
        String filename = "output.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }
}
