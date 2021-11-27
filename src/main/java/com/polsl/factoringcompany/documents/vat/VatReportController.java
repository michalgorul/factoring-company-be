package com.polsl.factoringcompany.documents.vat;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/report/vat")
public class VatReportController {

    private VatReportService vatReportService;

    @GetMapping(path = "/{customerId}")
    public ResponseEntity<byte[]> getVatReportPdf(@PathVariable Long customerId) throws Exception {

        byte[] pdf = vatReportService.generateDocxFileFromTemplate(customerId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        // Here you have to set the actual filename of your pdf
        String filename = "output.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);

    }

    @GetMapping(path = "/customer/{customerId}")
    public VatReportInformation getVatInformationFromNip(@PathVariable Long customerId) {
        return this.vatReportService.getCustomerVatInformation(customerId);
    }
}
