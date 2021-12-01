package com.polsl.factoringcompany.user;


import com.polsl.factoringcompany.exceptions.IdNotFoundInDatabaseException;
import com.polsl.factoringcompany.exceptions.NotUniqueException;
import com.polsl.factoringcompany.exceptions.ValueImproperException;
import com.polsl.factoringcompany.registration.RegistrationRequest;
import com.polsl.factoringcompany.stringvalidation.StringValidator;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * The type User service. Used to connect controller with Data access object
 * @author Michal Goral
 * @version 1.0
 */
@Service
@AllArgsConstructor
public class UserService {

    /**
     * the bean of user repository
     */
    private final UserRepository userRepository;

    /**
     * the bean of encryption tool
     */
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Gets all users.
     *
     * @return the users
     */
    public List<UserEntity> getUsers() {
        return this.userRepository.findAll();
    }

    /**
     * Gets desired user. If user is not found it throws IdNotFoundInDatabaseException
     *
     * @param id the id of desired user
     * @return the desired user
     */
    public UserEntity getUser(Long id) {
        try{
            return this.userRepository.findAllById(Collections.singleton(id)).get(0);
        }
        catch (IndexOutOfBoundsException e){
            throw new IdNotFoundInDatabaseException("User", id);
        }
    }

    /**
     * Gets currently logged user in JWT token user id.
     *
     * @return the currently logged user in JWT token user id
     */
    public Long getCurrentUserId() {
        Long id;
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (userRepository.findByUsername(currentUserName).isPresent()) {
            id = userRepository.findByUsername(currentUserName).get().getId();
            return id;
        }
        return null;
    }

    /**
     * Gets currently logged user in JWT token user.
     *
     * @return the currently logged user in JWT token
     */
    public UserEntity getCurrentUser() {
        return getUser(getCurrentUserId());
    }

    /**
     * Updates currently logged user's in JWT token entity.
     *
     * @param userRequestDto the user request dto
     * @return the user entity
     */
    public UserEntity updateCurrentUser(UserRequestDto userRequestDto) {

        Long currentUserId = getCurrentUserId();
        Optional<UserEntity> userEntityOptional = userRepository.findById(currentUserId);

        if (userEntityOptional.isEmpty())
            throw new IdNotFoundInDatabaseException("User", currentUserId);

        updateValidate(currentUserId, userRequestDto);

        try {
            userEntityOptional.get().setUsername(userEntityOptional.get().getUsername());
            userEntityOptional.get().setPassword(userEntityOptional.get().getPassword());
            userEntityOptional.get().setEmail(userRequestDto.getEmail());
            userEntityOptional.get().setFirstName(StringUtils.capitalize(userRequestDto.getFirstName()));
            userEntityOptional.get().setLastName(StringUtils.capitalize(userRequestDto.getLastName()));
            userEntityOptional.get().setCountry(StringUtils.capitalize(userRequestDto.getCountry()));
            userEntityOptional.get().setCity(StringUtils.capitalize(userRequestDto.getCity()));
            userEntityOptional.get().setStreet(StringUtils.capitalize(userRequestDto.getStreet()));
            userEntityOptional.get().setPostalCode(userRequestDto.getPostalCode());
            userEntityOptional.get().setPhone(userRequestDto.getPhone());
            userEntityOptional.get().setCompanyId(userEntityOptional.get().getCompanyId());


            return this.userRepository.save(userEntityOptional.get());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Update current user company id.
     *
     * @param companyId the company id
     */
    public void updateCurrentUserCompanyId(Integer companyId) {

        Long currentUserId = getCurrentUserId();
        Optional<UserEntity> userEntityOptional = userRepository.findById(currentUserId);

        if (userEntityOptional.isEmpty())
            throw new IdNotFoundInDatabaseException("User", currentUserId);

        try {
            userEntityOptional.get().setCompanyId(companyId);
            this.userRepository.save(userEntityOptional.get());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Add user user entity.
     *
     * @param userEntity the user entity
     * @return the user entity
     */
    public UserEntity addUser(UserEntity userEntity) {

        addValidate(userEntity);

        try {
            return this.userRepository.save(new UserEntity(
                    userEntity.getUsername(),
                    userEntity.getPassword(),
                    userEntity.getEmail(),
                    StringUtils.capitalize(userEntity.getFirstName()),
                    StringUtils.capitalize(userEntity.getLastName()),
                    StringUtils.capitalize(userEntity.getCountry()),
                    StringUtils.capitalize(userEntity.getCity()),
                    StringUtils.capitalize(userEntity.getStreet()),
                    userEntity.getPostalCode(),
                    userEntity.getPhone(),
                    userEntity.getCompanyId()));

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Registers new user. It performs encryption of password
     *
     * @param registrationRequest the registration request
     * @return the user entity
     */
    public UserEntity registerNewUser(RegistrationRequest registrationRequest) {
        addValidate(registrationRequest);

        registrationRequest.setPassword(bCryptPasswordEncoder.encode(registrationRequest.getPassword()));

        try {
            return this.userRepository.save(new UserEntity(
                    registrationRequest,
                    false,
                    false));

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates desired user entity.
     *
     * @param id         the id of user to update
     * @param userEntity the user entity
     * @return the user entity
     */
    public UserEntity updateUser(Long id, UserEntity userEntity) {

        Optional<UserEntity> userEntityOptional = userRepository.findById(id);

        if (userEntityOptional.isEmpty())
            throw new IdNotFoundInDatabaseException("User", id);

        updateValidate(id, userEntity);

        try {
            userEntityOptional.get().setUsername(userEntity.getUsername());
            userEntityOptional.get().setPassword(userEntity.getPassword());
            userEntityOptional.get().setEmail(userEntity.getEmail());
            userEntityOptional.get().setFirstName(StringUtils.capitalize(userEntity.getFirstName()));
            userEntityOptional.get().setLastName(StringUtils.capitalize(userEntity.getLastName()));
            userEntityOptional.get().setCountry(StringUtils.capitalize(userEntity.getCountry()));
            userEntityOptional.get().setCity(StringUtils.capitalize(userEntity.getCity()));
            userEntityOptional.get().setStreet(StringUtils.capitalize(userEntity.getStreet()));
            userEntityOptional.get().setPostalCode(userEntity.getPostalCode());
            userEntityOptional.get().setPhone(userEntity.getPhone());
            userEntityOptional.get().setCompanyId(userEntity.getCompanyId());


            return this.userRepository.save(userEntityOptional.get());
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes desired user.
     *
     * @param id the id of user to be deleted
     */
    public void deleteUser(Long id) {
        try {
            this.userRepository.deleteById(id);
        } catch (RuntimeException ex) {
            throw new IdNotFoundInDatabaseException("User", id);
        }
    }

    /**
     * Validates if update function can be performed
     * @param id the id of user that will be updated
     * @param userEntity the user entity
     */
    private void updateValidate(Long id, UserEntity userEntity) {

        if (ifEmailTakenUpdating(id, userEntity.getEmail()))
            throw new NotUniqueException("User", "email", userEntity.getEmail());

        if (ifUsernameTakenUpdating(id, userEntity.getUsername()))
            throw new NotUniqueException("User", "username", userEntity.getUsername());

        nameValidator(userEntity);
    }

    /**
     * Validates if update function can be performed
     * @param id the id of user that will be updated
     * @param userRequestDto the user request DTO
     */
    private void updateValidate(Long id, UserRequestDto userRequestDto) {

        if (ifEmailTakenUpdating(id, userRequestDto.getEmail()))
            throw new NotUniqueException("User", "email", userRequestDto.getEmail());

        nameValidator(userRequestDto);
    }

    /**
     * Validates if creation of new user function can be performed
     * @param userEntity the user entity
     */
    private void addValidate(UserEntity userEntity) {

        boolean emailValid = StringValidator.isEmailValid(userEntity.getEmail());

        if (!emailValid) {
            throw new IllegalArgumentException("email not valid");
        }

        if (ifEmailTakenAdding(userEntity.getEmail()))
            throw new NotUniqueException("User", "email", userEntity.getEmail());

        if (ifUsernameTakenAdding(userEntity.getUsername()))
            throw new NotUniqueException("User", "username", userEntity.getUsername());

        nameValidator(userEntity);
    }

    /**
     * Validates if creation of new user function can be performed
     * @param registrationRequest the registration request DTO
     */
    private void addValidate(RegistrationRequest registrationRequest) {

        if (ifEmailTakenAdding(registrationRequest.getEmail()))
            throw new NotUniqueException("User", "email", registrationRequest.getEmail());

        if (ifUsernameTakenAdding(registrationRequest.getUsername()))
            throw new NotUniqueException("User", "username", registrationRequest.getUsername());

        nameValidator(registrationRequest);
    }

    /**
     * Checks if email is already in database while creating new user entity
     * @param email the email
     * @return true if email is taken
     */
    private boolean ifEmailTakenAdding(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    /**
     * Checks if email is already in database while updating registered user entity
     * @param id the id of user
     * @param email the email
     * @return true if email is taken
     */
    private boolean ifEmailTakenUpdating(Long id, String email) {
        Optional<UserEntity> userEntityByEmail = userRepository.findByEmail(email);
        Optional<UserEntity> userEntityById = userRepository.findById(id);

        if (userEntityById.isEmpty())
            throw new IdNotFoundInDatabaseException("User", id);
        if (userEntityByEmail.isEmpty())
            return false;

        return !userEntityByEmail.get().getId().equals(userEntityById.get().getId());
    }

    /**
     * Checks if username is already in database while creating new user entity
     * @param username the username
     * @return true if username is taken
     */
    private boolean ifUsernameTakenAdding(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    /**
     * Checks if username is already in database while updating registered user entity
     * @param id the id of user
     * @param username the username
     * @return true if username is taken
     */
    private boolean ifUsernameTakenUpdating(Long id, String username) {
        Optional<UserEntity> userEntityByUsername = userRepository.findByUsername(username);
        Optional<UserEntity> userEntityById = userRepository.findById(id);

        if (userEntityById.isEmpty())
            throw new IdNotFoundInDatabaseException("User", id);
        if (userEntityByUsername.isEmpty())
            return false;

        return !userEntityByUsername.get().getId().equals(userEntityById.get().getId());
    }

    /**
     * Validates all strings in user entity if they are in proper form. Throws an exception if not
     * @param userEntity the user entity
     */
    private void nameValidator(UserEntity userEntity) {
        if (StringValidator.stringWithDigitsImproper(userEntity.getUsername(), 50)) {
            throw new ValueImproperException(userEntity.getUsername());
        } else if (!StringValidator.isEmailValid(userEntity.getEmail())) {
            throw new ValueImproperException(userEntity.getEmail());
        } else if (StringValidator.stringWithSpacesImproper(userEntity.getCountry(), 50)) {
            throw new ValueImproperException(userEntity.getCountry());
        } else if (StringValidator.stringWithSpacesImproper(userEntity.getCity(), 50)) {
            throw new ValueImproperException(userEntity.getCity());
        } else if (StringValidator.stringWithSpacesImproper(userEntity.getStreet(), 50)) {
            throw new ValueImproperException(userEntity.getStreet());
        } else if (StringValidator.stringWithDigitsImproper(userEntity.getPostalCode(), 15)) {
            throw new ValueImproperException(userEntity.getPostalCode());
        } else if (!StringValidator.isPhoneNumberValid(userEntity.getPhone())) {
            throw new ValueImproperException(userEntity.getPhone());

        }
    }

    /**
     * Validates all strings in user entity if they are in proper form. Throws an exception if not
     * @param registrationRequest the user registration request DTO
     */
    private void nameValidator(RegistrationRequest registrationRequest) {
        if (StringValidator.stringWithDigitsImproper(registrationRequest.getUsername(), 50)) {
            throw new ValueImproperException(registrationRequest.getUsername());
        } else if (!StringValidator.isEmailValid(registrationRequest.getEmail())) {
            throw new ValueImproperException(registrationRequest.getEmail());
        } else if (StringValidator.stringWithSpacesImproper(registrationRequest.getCountry(), 50)) {
            throw new ValueImproperException(registrationRequest.getCountry());
        } else if (StringValidator.stringWithSpacesImproper(registrationRequest.getCity(), 50)) {
            throw new ValueImproperException(registrationRequest.getCity());
        } else if (StringValidator.stringWithSpacesAndDigitsImproper(registrationRequest.getStreet(), 50)) {
            throw new ValueImproperException(registrationRequest.getStreet());
        } else if (StringValidator.postalCodeImproper(registrationRequest.getPostalCode(), 15)) {
            throw new ValueImproperException(registrationRequest.getPostalCode());
        } else if (!StringValidator.isPhoneNumberValid(registrationRequest.getPhone())) {
            throw new ValueImproperException(registrationRequest.getPhone());
        } else if (!StringValidator.isPasswordValid(registrationRequest.getPassword())) {
            throw new ValueImproperException(registrationRequest.getPassword());
        }

    }

    /**
     * Validates if password is in proper form while updating this field
     * @param password the password
     */
    private void passwordUpdateValidator(String password) {
        if (!StringValidator.isPasswordValid(password)) {
            throw new ValueImproperException(password);
        }
    }

    /**
     * Validates all strings in user entity if they are in proper form. Throws an exception if not
     * @param userRequestDto the user request DTO
     */
    private void nameValidator(UserRequestDto userRequestDto) {

        if (!StringValidator.isEmailValid(userRequestDto.getEmail())) {
            throw new ValueImproperException(userRequestDto.getEmail());
        } else if (StringValidator.stringWithSpacesImproper(userRequestDto.getCountry(), 50)) {
            throw new ValueImproperException(userRequestDto.getCountry());
        } else if (StringValidator.stringWithSpacesImproper(userRequestDto.getCity(), 50)) {
            throw new ValueImproperException(userRequestDto.getCity());
        } else if (StringValidator.stringWithSpacesImproper(userRequestDto.getStreet(), 50)) {
            throw new ValueImproperException(userRequestDto.getStreet());
        } else if (StringValidator.stringWithDigitsImproper(userRequestDto.getPostalCode(), 15)) {
            throw new ValueImproperException(userRequestDto.getPostalCode());
        } else if (!StringValidator.isPhoneNumberValid(userRequestDto.getPhone())) {
            throw new ValueImproperException(userRequestDto.getPhone());
        }
    }

    /**
     * Enables app user.
     *
     * @param id the id of user to be enabled
     */
    public void enableAppUser(Long id) {
        userRepository.enableAppUser(id);
    }

    /**
     * Gets user by email.
     *
     * @param email the email of desired user
     * @return the user entity
     */
    public UserEntity getUserByEmail(String email) {
        return this.userRepository.findByEmail(email)
                .orElseThrow(() -> new IdNotFoundInDatabaseException("User", 0L));
    }

    /**
     * Update users password.
     *
     * @param id       the id of desired user
     * @param password the new password
     */
    public void updateUsersPassword(Long id, String password) {

        UserEntity userEntityOptional = getUser(id);

        passwordUpdateValidator(password);

        try {
            assert userEntityOptional != null;
            userEntityOptional.setPassword(bCryptPasswordEncoder.encode(password));
            this.userRepository.save(userEntityOptional);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }
}
