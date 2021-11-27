package com.polsl.factoringcompany.company;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/company")
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    public List<CompanyEntity> getCompanies() {
        return this.companyService.getCompanies();
    }

    @GetMapping(path = "/{id}")
    public CompanyEntity getCompany(@PathVariable Long id) {
        return this.companyService.getCompany(id);
    }

    @GetMapping(path = "/current")
    public CompanyEntity getCurrentUserCompany() {
        return this.companyService.getCurrentUserCompany();
    }

    @PutMapping("/current")
    public CompanyEntity updateCurrentCompany(@RequestBody CompanyRequestDto companyRequestDto) {
        return this.companyService.updateCurrentUserCompany(companyRequestDto);
    }

    @PostMapping("/current")
    public CompanyEntity createCurrentUserCompany(@RequestBody CompanyRequestDto companyRequestDto) {
        return this.companyService.createCurrentUserCompany(companyRequestDto);
    }

    @PostMapping("/customer/{id}")
    public CompanyEntity createCustomerCompany(@PathVariable Long id, @RequestBody CompanyRequestDto companyRequestDto) {
        return this.companyService.createCustomerCompany(Math.toIntExact(id), companyRequestDto);
    }

    @PutMapping("/{id}")
    public CompanyEntity updateCompany(@PathVariable Long id, @RequestBody CompanyRequestDto companyRequestDto) {
        return this.companyService.updateCompany(id, companyRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable Long id) {
        this.companyService.deleteCompany(id);
    }
}
