package com.polsl.factoringcompany.company;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type company controller. Class for creating endpoints.
 * @author Michal Goral
 * @version 1.0
 */
@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/company")
public class CompanyController {

    /**
     * the company service bean
     */
    private final CompanyService companyService;

    /**
     * Gets all companies from database.
     *
     * @return the companies
     */
    @GetMapping
    public List<CompanyEntity> getCompanies() {
        return this.companyService.getCompanies();
    }

    /**
     * Gets company specified by id.
     *
     * @param id the id
     * @return the company
     */
    @GetMapping(path = "/{id}")
    public CompanyEntity getCompany(@PathVariable Long id) {
        return this.companyService.getCompany(id);
    }

    /**
     * Gets currently logged user's company.
     *
     * @return the current user company
     */
    @GetMapping(path = "/current")
    public CompanyEntity getCurrentUserCompany() {
        return this.companyService.getCurrentUserCompany();
    }

    /**
     * Updates currently logged user's company entity.
     *
     * @param companyRequestDto the company request dto
     * @return the company entity
     */
    @PutMapping("/current")
    public CompanyEntity updateCurrentCompany(@RequestBody CompanyRequestDto companyRequestDto) {
        return this.companyService.updateCurrentUserCompany(companyRequestDto);
    }

    /**
     * Creates currently logged user's company entity.
     *
     * @param companyRequestDto the company request dto
     * @return the company entity
     */
    @PostMapping("/current")
    public CompanyEntity createCurrentUserCompany(@RequestBody CompanyRequestDto companyRequestDto) {
        return this.companyService.createCurrentUserCompany(companyRequestDto);
    }

    /**
     * Creates customer's company entity who is specified by id.
     *
     * @param id                the customer id
     * @param companyRequestDto the company request dto
     * @return the company entity
     */
    @PostMapping("/customer/{id}")
    public CompanyEntity createCustomerCompany(@PathVariable Long id, @RequestBody CompanyRequestDto companyRequestDto) {
        return this.companyService.createCustomerCompany(Math.toIntExact(id), companyRequestDto);
    }

    /**
     * Updates company entity and saves it to database.
     *
     * @param id                the id
     * @param companyRequestDto the company request dto
     * @return the company entity
     */
    @PutMapping("/{id}")
    public CompanyEntity updateCompany(@PathVariable Long id, @RequestBody CompanyRequestDto companyRequestDto) {
        return this.companyService.updateCompany(id, companyRequestDto);
    }

    /**
     * Deletes company specified by id from database.
     *
     * @param id the id
     */
    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable Long id) {
        this.companyService.deleteCompany(id);
    }
}
