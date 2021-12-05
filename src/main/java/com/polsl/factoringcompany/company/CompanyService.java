package com.polsl.factoringcompany.company;


import com.polsl.factoringcompany.customer.CustomerService;
import com.polsl.factoringcompany.exceptions.IdNotFoundInDatabaseException;
import com.polsl.factoringcompany.exceptions.NotUniqueException;
import com.polsl.factoringcompany.exceptions.ValueImproperException;
import com.polsl.factoringcompany.stringvalidation.StringValidator;
import com.polsl.factoringcompany.user.UserEntity;
import com.polsl.factoringcompany.user.UserRepository;
import com.polsl.factoringcompany.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

/**
 * The type company service. Used to connect controller with Data access object
 *
 * @author Michal Goral
 * @version 1.0
 */
@Service
@AllArgsConstructor
public class CompanyService {

    /**
     * the company repository bean
     */
    private final CompanyRepository companyRepository;

    /**
     * the user repository bean
     */
    private UserRepository userRepository;

    /**
     * the user service bean
     */
    private UserService userService;

    /**
     * the customer service bean
     */
    private CustomerService customerService;

    /**
     * Gets all companies from database.
     *
     * @return the companies
     */
    public List<CompanyEntity> getCompanies() {
        return this.companyRepository.findAll();
    }

    /**
     * Gets company specified by id.
     *
     * @param id the id
     * @return the company
     */
    public CompanyEntity getCompany(Long id) {
        return this.companyRepository.findById(id)
                .orElseThrow(() -> new IdNotFoundInDatabaseException("Company not found"));
    }

    /**
     * Updates company entity and saves it to database.
     *
     * @param id                the id
     * @param companyRequestDto the company request dto
     * @return the company entity
     */
    public CompanyEntity updateCompany(Long id, CompanyRequestDto companyRequestDto) {

        Optional<CompanyEntity> companyEntityOptional = companyRepository.findById(id);

        if (companyEntityOptional.isEmpty())
            throw new IdNotFoundInDatabaseException("Company", id);

        updateValidate(id, companyRequestDto);

        try {
            companyEntityOptional.get().setCompanyName(StringUtils.capitalize(companyRequestDto.getCompanyName()));
            companyEntityOptional.get().setCountry(StringUtils.capitalize(companyRequestDto.getCountry()));
            companyEntityOptional.get().setCity(StringUtils.capitalize(companyRequestDto.getCity()));
            companyEntityOptional.get().setStreet(StringUtils.capitalize(companyRequestDto.getStreet()));
            companyEntityOptional.get().setPostalCode(companyRequestDto.getPostalCode());
            companyEntityOptional.get().setNip(companyRequestDto.getNip());
            companyEntityOptional.get().setRegon(companyRequestDto.getRegon());
            return this.companyRepository.save(companyEntityOptional.get());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes company from database.
     *
     * @param id the id
     */
    public void deleteCompany(Long id) {
        try {
            this.companyRepository.deleteById(id);
        } catch (RuntimeException ex) {
            throw new IdNotFoundInDatabaseException("Company", id);
        }
    }

    /**
     * Checks if NIP number is already in use while creating new company entity
     * @param nip the nip number
     * @return true if nip is in use
     */
    private boolean ifNipTakenAdding(String nip) {
        Optional<CompanyEntity> companyEntity = companyRepository.findCompanyEntityByNip(nip);
        return companyEntity.isPresent();
    }

    /**
     * Checks if REGON number is already in use while creating new company entity
     * @param regon the regon number
     * @return true if regon is in use
     */
    private boolean ifRegonTakenAdding(String regon) {
        Optional<CompanyEntity> companyEntity = companyRepository.findCompanyEntityByRegon(regon);
        return companyEntity.isPresent();
    }

    /**
     * Checks if NIP number is already in use while updating existing company entity
     * @param nip the nip number
     * @return true if nip is in use
     */
    private boolean ifNipTakenUpdating(Long id, String nip) {
        Optional<CompanyEntity> companyEntityByNip = companyRepository.findCompanyEntityByNip(nip);
        Optional<CompanyEntity> companyEntityById = companyRepository.findById(id);

        if (companyEntityById.isEmpty())
            throw new IdNotFoundInDatabaseException("Company", id);
        if (companyEntityByNip.isEmpty())
            return false;

        return !companyEntityByNip.get().getId().equals(companyEntityById.get().getId());
    }

    /**
     * Checks if REGON number is already in use while updating existing company entity
     * @param regon the regon number
     * @return true if regon is in use
     */
    private boolean ifRegonTakenUpdating(Long id, String regon) {
        Optional<CompanyEntity> companyEntityByRegon = companyRepository.findCompanyEntityByRegon(regon);
        Optional<CompanyEntity> companyEntityById = companyRepository.findById(id);

        if (companyEntityById.isEmpty())
            throw new IdNotFoundInDatabaseException("Company", id);
        if (companyEntityByRegon.isEmpty())
            return false;

        return !companyEntityByRegon.get().getId().equals(companyEntityById.get().getId());
    }

    private void updateValidate(Long id, CompanyRequestDto companyEntity) {
        if (ifNipTakenUpdating(id, companyEntity.getNip()))
            throw new NotUniqueException("Company", "NIP", companyEntity.getNip());

        else if (ifRegonTakenUpdating(id, companyEntity.getRegon()))
            throw new NotUniqueException("Company", "REGON", companyEntity.getRegon());

        nameValidator(companyEntity);
    }

    /**
     * Validates if NIP and REGON are valid while creating new company entity
     * @param companyEntity the company entity
     */
    private void addValidate(CompanyRequestDto companyEntity) {
        if (ifNipTakenAdding(companyEntity.getNip()))
            throw new NotUniqueException("Company", "NIP", companyEntity.getNip());

        else if (ifRegonTakenAdding(companyEntity.getRegon()))
            throw new NotUniqueException("Company", "REGON", companyEntity.getRegon());

        nameValidator(companyEntity);
    }

    /**
     * Validates if names are in proper form while creating new company entity
     * @param companyEntity the company entity
     */
    private void nameValidator(CompanyRequestDto companyEntity) {
        if (StringValidator.stringWithSpacesImproper(companyEntity.getCompanyName(), 50)) {
            throw new ValueImproperException(companyEntity.getCompanyName());
        } else if (StringValidator.stringWithSpacesImproper(companyEntity.getCountry(), 50)) {
            throw new ValueImproperException(companyEntity.getCountry());
        } else if (StringValidator.stringWithSpacesImproper(companyEntity.getCity(), 50)) {
            throw new ValueImproperException(companyEntity.getCity());
        } else if (StringValidator.stringWithSpacesImproper(companyEntity.getStreet(), 50)) {
            throw new ValueImproperException(companyEntity.getStreet());
        } else if (StringValidator.stringWithDigitsImproper(companyEntity.getPostalCode(), 15)) {
            throw new ValueImproperException(companyEntity.getPostalCode());
        } else if (StringValidator.ifNotDigitsOnly(companyEntity.getNip()))
            throw new ValueImproperException(companyEntity.getNip(), "NIP");

        else if (StringValidator.ifNotDigitsOnly(companyEntity.getRegon()))
            throw new ValueImproperException(companyEntity.getRegon(), "REGON");
    }

    /**
     * Gets currently logged user in JWT token company.
     *
     * @return the current user company
     */
    public CompanyEntity getCurrentUserCompany() {

        Long id = 0L;
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (userRepository.findByUsername(currentUserName).isPresent()) {
            id = userRepository.findByUsername(currentUserName).get().getId();
        }

        Optional<UserEntity> userEntityOptional = userRepository.findById(id);

        if (userEntityOptional.isEmpty())
            throw new IdNotFoundInDatabaseException("User", id);

        if (userEntityOptional.get().getCompanyId() != null) {
            return getCompany((long) userEntityOptional.get().getCompanyId());
        } else return null;

    }

    /**
     * Updates currently logged in JWT token user a company entity.
     *
     * @param companyRequestDto the company request dto
     * @return the company entity
     */
    public CompanyEntity updateCurrentUserCompany(CompanyRequestDto companyRequestDto) {
        UserEntity currentUser = userService.getCurrentUser();
        Optional<CompanyEntity> companyEntityOptional = companyRepository.findById((long) currentUser.getCompanyId());

        if (companyEntityOptional.isEmpty())
            throw new IdNotFoundInDatabaseException("Company", 0L);

        updateValidate(companyEntityOptional.get().getId(), companyRequestDto);

        try {
            companyEntityOptional.get().setCompanyName(StringUtils.capitalize(companyRequestDto.getCompanyName()));
            companyEntityOptional.get().setCountry(StringUtils.capitalize(companyRequestDto.getCountry()));
            companyEntityOptional.get().setCity(StringUtils.capitalize(companyRequestDto.getCity()));
            companyEntityOptional.get().setStreet(StringUtils.capitalize(companyRequestDto.getStreet()));
            companyEntityOptional.get().setPostalCode(companyRequestDto.getPostalCode());
            companyEntityOptional.get().setNip(companyRequestDto.getNip());
            companyEntityOptional.get().setRegon(companyRequestDto.getRegon());
            return this.companyRepository.save(companyEntityOptional.get());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates currently logged user in JWT token a company entity.
     *
     * @param companyRequestDto the company request dto
     * @return the company entity
     */
    public CompanyEntity createCurrentUserCompany(CompanyRequestDto companyRequestDto) {
        addValidate(companyRequestDto);
        try {
            CompanyEntity companyEntity = this.companyRepository.save(new CompanyEntity(
                    StringUtils.capitalize(companyRequestDto.getCompanyName()),
                    StringUtils.capitalize(companyRequestDto.getCountry()),
                    StringUtils.capitalize(companyRequestDto.getCity()),
                    StringUtils.capitalize(companyRequestDto.getStreet()),
                    companyRequestDto.getPostalCode(),
                    companyRequestDto.getNip(),
                    companyRequestDto.getRegon()));
            userService.updateCurrentUserCompanyId(Math.toIntExact(companyEntity.getId()));
            return companyEntity;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Creates customer specified by id a company entity.
     *
     * @param customerId        the customer id
     * @param companyRequestDto the company request dto
     * @return the company entity
     */
    public CompanyEntity createCustomerCompany(int customerId, CompanyRequestDto companyRequestDto) {
        addValidate(companyRequestDto);
        try {
            CompanyEntity companyEntity = this.companyRepository.save(new CompanyEntity(
                    StringUtils.capitalize(companyRequestDto.getCompanyName()),
                    StringUtils.capitalize(companyRequestDto.getCountry()),
                    StringUtils.capitalize(companyRequestDto.getCity()),
                    StringUtils.capitalize(companyRequestDto.getStreet()),
                    companyRequestDto.getPostalCode(),
                    companyRequestDto.getNip(),
                    companyRequestDto.getRegon()));
            customerService.updateCustomerCompanyId(customerId, Math.toIntExact(companyEntity.getId()));
            return companyEntity;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
