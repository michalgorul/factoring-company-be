package com.polsl.factoringcompany.customer;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Customer controller. Class for creating endpoints.
 * @author Michal Goral
 * @version 1.0
 */
@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/customer")
public class CustomerController {

    /**
     * the customer service bean
     */
    private final CustomerService customerService;

    /**
     * Gets all customers from database.
     *
     * @return the customers
     */
    @GetMapping
    public List<CustomerEntity> getCustomers() {
        return customerService.getCustomers();
    }

    /**
     * Gets currently logged in JWT token user's customers.
     *
     * @return the current user customers
     */
    @GetMapping(path = "/current")
    public List<CustomerEntity> getCurrentUserCustomers() {
        return customerService.getCurrentUserCustomers();
    }

    /**
     * Gets customer specified by id.
     *
     * @param id the id
     * @return the customer
     */
    @GetMapping(path = "/{id}")
    public CustomerEntity getCustomer(@PathVariable Long id) {
        return this.customerService.getCustomer(id);
    }

    /**
     * Gets customer specified by phone.
     *
     * @param phone the phone
     * @return the customer by phone
     */
    @GetMapping(path = "/phone/{phone}")
    public CustomerEntity getCustomerByPhone(@PathVariable String phone) {
        return this.customerService.getCustomerByPhone(phone);
    }

    /**
     * Creates customer entity and saves it to database.
     *
     * @param customerRequestDto the customer request dto
     * @return the customer entity
     */
    @PostMapping
    public CustomerEntity addCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
        return this.customerService.addCustomer(customerRequestDto);
    }

    /**
     * Updates customer entity and saves it to database.
     *
     * @param id                 the id
     * @param customerRequestDto the customer request dto
     * @return the customer entity
     */
    @PutMapping("/{id}")
    public CustomerEntity updateCustomer(@PathVariable Long id, @RequestBody CustomerRequestDto customerRequestDto) {
        return this.customerService.updateCustomer(id, customerRequestDto);
    }

    /**
     * Deletes customer from database.
     *
     * @param id the id
     */
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}
