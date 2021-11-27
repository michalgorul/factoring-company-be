package com.polsl.factoringcompany.customer;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public List<CustomerEntity> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping(path = "/current")
    public List<CustomerEntity> getCurrentUserCustomers() {
        return customerService.getCurrentUserCustomers();
    }

    @GetMapping(path = "/{id}")
    public CustomerEntity getCustomer(@PathVariable Long id) {
        return this.customerService.getCustomer(id);
    }

    @GetMapping(path = "/phone/{phone}")
    public CustomerEntity getCustomerByPhone(@PathVariable String phone) {
        return this.customerService.getCustomerByPhone(phone);
    }

    @PostMapping
    public CustomerEntity addCustomer(@RequestBody CustomerRequestDto customerRequestDto) {
        return this.customerService.addCustomer(customerRequestDto);
    }

    @PutMapping("/{id}")
    public CustomerEntity updateCustomer(@PathVariable Long id, @RequestBody CustomerRequestDto customerRequestDto) {
        return this.customerService.updateCustomer(id, customerRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}
