package com.polsl.factoringcompany.customer;

import com.polsl.factoringcompany.exceptions.IdNotFoundInDatabaseException;
import com.polsl.factoringcompany.exceptions.ValueImproperException;
import com.polsl.factoringcompany.stringvalidation.StringValidator;
import com.polsl.factoringcompany.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

/**
 * The type customer service. Used to connect controller with Data access object
 * @author Michal Goral
 * @version 1.0
 */
@Service
@AllArgsConstructor
public class CustomerService {

    /**
     * the customer repository bean
     */
    private final CustomerRepository customerRepository;

    /**
     * the user service bean
     */
    private final UserService userService;

    /**
     * Gets all customers from database.
     *
     * @return the customers
     */
    public List<CustomerEntity> getCustomers() {
        return this.customerRepository.findAll();
    }

    /**
     * Gets customer specified by id.
     *
     * @param id the id
     * @return the customer
     */
    public CustomerEntity getCustomer(Long id) {
        return this.customerRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundInDatabaseException("Customer", id));
    }

    /**
     * Creates customer entity and saves it to database.
     *
     * @param customerRequestDto the customer request dto
     * @return the customer entity
     */
    public CustomerEntity addCustomer(CustomerRequestDto customerRequestDto) {

        nameValidator(customerRequestDto);

        try {
            return this.customerRepository.save(new CustomerEntity(
                    StringUtils.capitalize(customerRequestDto.getFirstName()),
                    StringUtils.capitalize(customerRequestDto.getLastName()),
                    StringUtils.capitalize(customerRequestDto.getCompanyName()),
                    StringUtils.capitalize(customerRequestDto.getCountry()),
                    StringUtils.capitalize(customerRequestDto.getCity()),
                    StringUtils.capitalize(customerRequestDto.getStreet()),
                    customerRequestDto.getPostalCode(),
                    customerRequestDto.getPhone(),
                    customerRequestDto.isBlacklisted(),
                    Math.toIntExact(userService.getCurrentUserId()),
                    customerRequestDto.getEmail()));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes customer from database specified by id.
     *
     * @param id the id
     */
    public void deleteCustomer(Long id) {
        try {
            this.customerRepository.deleteById(id);
        } catch (RuntimeException ex) {
            throw new IdNotFoundInDatabaseException("Customer", id);
        }
    }

    /**
     * Updates customer entity and saves it to database.
     *
     * @param id                 the id
     * @param customerRequestDto the customer request dto
     * @return the customer entity
     */
    public CustomerEntity updateCustomer(Long id, CustomerRequestDto customerRequestDto) {

        Optional<CustomerEntity> customerEntityOptional = customerRepository.findById(id);

        if (customerEntityOptional.isEmpty())
            throw new IdNotFoundInDatabaseException("Customer", id);

        nameValidator(customerRequestDto);

        try {
            customerEntityOptional.get().setEmail(customerRequestDto.getEmail());
            customerEntityOptional.get().setFirstName(StringUtils.capitalize(customerRequestDto.getFirstName()));
            customerEntityOptional.get().setLastName(StringUtils.capitalize(customerRequestDto.getLastName()));
            customerEntityOptional.get().setCompanyName(StringUtils.capitalize(customerRequestDto.getCompanyName()));
            customerEntityOptional.get().setCountry(StringUtils.capitalize(customerRequestDto.getCountry()));
            customerEntityOptional.get().setCity(StringUtils.capitalize(customerRequestDto.getCity()));
            customerEntityOptional.get().setStreet(StringUtils.capitalize(customerRequestDto.getStreet()));
            customerEntityOptional.get().setPostalCode(customerRequestDto.getPostalCode());
            customerEntityOptional.get().setPhone(customerRequestDto.getPhone());
            customerEntityOptional.get().setBlacklisted(customerRequestDto.isBlacklisted());

            return this.customerRepository.save(customerEntityOptional.get());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Validates if names for customer entity are in proper form
     * @param customerEntity the customer entity
     */
    private void nameValidator(CustomerRequestDto customerEntity) {
        if (StringValidator.stringWithSpacesImproper(customerEntity.getFirstName(), 50)) {
            throw new ValueImproperException(customerEntity.getFirstName());
        } else if (StringValidator.stringWithSpacesImproper(customerEntity.getLastName(), 50)) {
            throw new ValueImproperException(customerEntity.getLastName());
        } else if (StringValidator.stringWithSpacesImproper(customerEntity.getCompanyName(), 50)) {
            throw new ValueImproperException(customerEntity.getCompanyName());
        } else if (StringValidator.stringWithSpacesImproper(customerEntity.getCountry(), 50)) {
            throw new ValueImproperException(customerEntity.getCountry());
        } else if (StringValidator.stringWithSpacesImproper(customerEntity.getCity(), 50)) {
            throw new ValueImproperException(customerEntity.getCity());
        } else if (StringValidator.stringWithSpacesImproper(customerEntity.getStreet(), 50)) {
            throw new ValueImproperException(customerEntity.getStreet());
        } else if (StringValidator.stringWithDigitsImproper(customerEntity.getPostalCode(), 15)) {
            throw new ValueImproperException(customerEntity.getPostalCode());
        }

    }


    /**
     * Gets currently logged in JWT token user customers.
     *
     * @return the current user customers
     */
    public List<CustomerEntity> getCurrentUserCustomers() {

        Long currentUserId = userService.getCurrentUserId();
        return this.customerRepository.findAllByUserId(Math.toIntExact(currentUserId));

    }

    /**
     * Updates customer company id.
     *
     * @param customerId the customer id
     * @param companyId  the company id
     */
    public void updateCustomerCompanyId(int customerId, int companyId) {

        Optional<CustomerEntity> customerEntity = customerRepository.findById((long) customerId);

        if (customerEntity.isEmpty())
            throw new IdNotFoundInDatabaseException("Customer", (long) customerId);

        try {
            customerEntity.get().setCompanyId(companyId);
            this.customerRepository.save(customerEntity.get());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets customer specified by phone.
     *
     * @param phone the phone
     * @return the customer by phone
     */
    public CustomerEntity getCustomerByPhone(String phone) {
        return this.customerRepository.findByPhone(phone)
                .orElseThrow(() -> new IdNotFoundInDatabaseException("Customer", 0L));
    }
}
