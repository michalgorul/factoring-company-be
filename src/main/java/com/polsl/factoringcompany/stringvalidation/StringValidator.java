package com.polsl.factoringcompany.stringvalidation;

import lombok.Getter;
import lombok.Setter;
import nl.garvelink.iban.IBAN;
import nl.garvelink.iban.IBANFields;
import nl.garvelink.iban.Modulo97;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.Optional;
import java.util.regex.Pattern;

/**
 * The String validator class. Contains static methods for validating some strings
 * @author Michal Goral
 * @version  1.0
 */
@Getter
@Setter
public class StringValidator {

    /**
     * Validates if string and contains only letters and spaces
     *
     * @param s the string to check
     * @return true if string is valid
     */
    public static boolean onlyLettersSpaces(String s) {
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isLetter(ch) || ch == ' ') {
                continue;
            }
            return true;
        }
        return s.charAt(s.length() - 1) == ' ';
    }


    /**
     * Validates if string contains only letters and digits
     *
     * @param s the string
     * @return true if string is valid
     */
    public static boolean onlyLettersDigits(String s) {
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isLetter(ch) || Character.isDigit(ch)) {
                continue;
            }
            return true;
        }
        return false;
    }

    /**
     * Validates if string contains something else than digits
     *
     * @param checkingString the checking string
     * @return true if string is valid
     */
    public static boolean ifNotDigitsOnly(String checkingString) {
        return !checkingString.chars().allMatch(Character::isDigit);
    }

    /**
     * Checks if string with spaces is improper.
     *
     * @param string the string
     * @param length the max length of string
     * @return true if string is improper
     */
    public static boolean stringWithSpacesImproper(String string, int length) {
        return string == null || string.length() <= 0 || string.length() > length || onlyLettersSpaces(string);
    }

    /**
     * Checks if string with digits and spaces is improper.
     *
     * @param string the string
     * @param length the max length of string
     * @return true if string is improper
     */
    public static boolean stringWithSpacesAndDigitsImproper(String string, int length) {
        return string == null || string.length() <= 0 || string.length() > length;
    }

    /**
     * Checks if string with digits is improper.
     *
     * @param string the string
     * @param length the max length of string
     * @return true if string is improper
     */
    public static boolean stringWithDigitsImproper(String string, int length) {
        return string == null || string.length() <= 0 || string.length() > length || onlyLettersDigits(string);
    }

    /**
     * Checks if string without spaces is improper.
     *
     * @param string the string
     * @param length the max length of string
     * @return true if string is improper
     */
    public static boolean stringWithoutSpacesImproper(String string, int length) {
        return string == null || string.length() <= 0 || string.length() > length || !string.chars().allMatch(Character::isLetter);
    }

    /**
     * Checks if postal code is improper.
     *
     * @param string the postal code
     * @param length the max length of postal code
     * @return true if postal code is improper
     */
    public static boolean postalCodeImproper(String string, int length) {
        return string == null || string.length() <= 0 || string.length() > length || isPostalCodeValid(string);
    }

    /**
     * Validates if postal code is valid. Postal code can contain digits, letters and '-'
     *
     * @param s the postal code
     * @return true if postal code is valid
     */
    public static boolean isPostalCodeValid(String s) {
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isLetter(ch) || Character.isDigit(ch) || ch == '-') {
                continue;
            }
            return true;
        }
        return false;
    }

    /**
     * Validates if bank account number is valid. It uses nl.garvelink.iban for checking
     * <a href="http://www.garvelink.nl/java-iban/">Link to library</a>
     *
     * @param candidate the candidate
     * @return true if bank account number is valid
     */
    public static boolean isBankAccountNumberValid(String candidate) {
        boolean valid = Modulo97.verifyCheckDigits(candidate);

        IBAN iban = IBAN.valueOf(candidate);

        Optional<String> bankId = IBANFields.getBankIdentifier(iban);
        Optional<String> branchId = IBANFields.getBranchIdentifier(iban);

        boolean isRegistered = IBAN.parse(candidate).isInSwiftRegistry(); // true

        return valid;
    }

    /**
     * Validates if email is valid. It uses org.apache.commons.validator.routines for checking.
     * <a href="https://mvnrepository.com/artifact/nl.garvelink.oss/iban">Link to maven repo</a>
     *
     * @param email the email
     * @return true if email is valid
     */
    public static boolean isEmailValid(String email) {
        // create the EmailValidator instance
        EmailValidator validator = EmailValidator.getInstance();

        // check for valid email addresses using isValid method
        return validator.isValid(email) && email.length() < 50;
    }

    /**
     * Validates if phone number is valid. It uses regex for checking
     *
     * @param phoneNumber the phone number
     * @return true if phone number is valid
     */
    public static boolean isPhoneNumberValid(String phoneNumber) {
        String patterns
                = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";

        Pattern pattern = Pattern.compile(patterns);

        return pattern.matcher(phoneNumber).matches();
    }

    /**
     * Validates if password is valid. It must consist of lowercase and uppercase letters, digits, symbols and
     * has length between 8-30
     *
     * @param password the password
     * @return true if password is valid
     */
    public static boolean isPasswordValid(String password) {
        String patterns = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[`!@#$%^&*()+=-_?/\\\\|~]).{8,30}$";
        Pattern pattern = Pattern.compile(patterns);
        return pattern.matcher(password).matches();
    }


}
