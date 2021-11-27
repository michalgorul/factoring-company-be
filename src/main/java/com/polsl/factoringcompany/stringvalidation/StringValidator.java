package com.polsl.factoringcompany.stringvalidation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import nl.garvelink.iban.IBAN;
import nl.garvelink.iban.IBANFields;
import nl.garvelink.iban.Modulo97;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.Optional;
import java.util.regex.Pattern;

@Getter
@Setter
@AllArgsConstructor
public class StringValidator {

    private String string;
    private int acceptableLength;

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

    public static boolean ifNotDigitsOnly(String checkingString) {
        return !checkingString.chars().allMatch(Character::isDigit);
    }

    public static boolean stringWithSpacesImproper(String string, int length) {
        return string == null || string.length() <= 0 || string.length() > length || onlyLettersSpaces(string);
    }

    public static boolean stringWithSpacesAndDigitsImproper(String string, int length) {
        return string == null || string.length() <= 0 || string.length() > length;
    }

    public static boolean stringWithDigitsImproper(String string, int length) {
        return string == null || string.length() <= 0 || string.length() > length || onlyLettersDigits(string);
    }

    public static boolean stringWithoutSpacesImproper(String string, int length) {
        return string == null || string.length() <= 0 || string.length() > length || !string.chars().allMatch(Character::isLetter);
    }

    public static boolean postalCodeImproper(String string, int length) {
        return string == null || string.length() <= 0 || string.length() > length || isPostalCodeValid(string);
    }

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

    public static boolean isBankAccountNumberValid(String candidate) {
        boolean valid = Modulo97.verifyCheckDigits(candidate);

        IBAN iban = IBAN.valueOf(candidate);

        Optional<String> bankId = IBANFields.getBankIdentifier(iban);
        Optional<String> branchId = IBANFields.getBranchIdentifier(iban);

        boolean isRegistered = IBAN.parse(candidate).isInSwiftRegistry(); // true

        return valid;
    }

    public static boolean isEmailValid(String email) {
        // create the EmailValidator instance
        EmailValidator validator = EmailValidator.getInstance();

        // check for valid email addresses using isValid method
        return validator.isValid(email) && email.length() < 50;
    }

    public static boolean isPhoneNumberValid(String phoneNumber) {
        String patterns
                = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";

        Pattern pattern = Pattern.compile(patterns);

        return pattern.matcher(phoneNumber).matches();
    }

    public static boolean isPasswordValid(String password) {
        String patterns = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[`!@#$%^&*()+=-_?/\\\\|~]).{8,30}$";
        Pattern pattern = Pattern.compile(patterns);
        return pattern.matcher(password).matches();
    }


}
