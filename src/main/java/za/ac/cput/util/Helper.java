package za.ac.cput.util;

import org.apache.commons.validator.routines.EmailValidator;

public class Helper {
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isValidPhoneNumber(String number) {
        return number.matches("^0[0-9]{10}$");
    }

    public static boolean isValidEmail(String email) {
        EmailValidator emailValidator = EmailValidator.getInstance();
        return emailValidator.isValid(email);
    }
}
