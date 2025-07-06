package co.com.ceiba.reto.common.domain.validator;

import co.com.ceiba.reto.common.domain.constant.ConstantText;
import co.com.ceiba.reto.common.domain.exception.LengthException;
import co.com.ceiba.reto.common.domain.exception.PatternException;
import co.com.ceiba.reto.common.domain.exception.ValidateMandatoryException;

public class TextValidator {
    private static final String LETTERS_AND_SPACES_PATTERN = "^[a-zA-ZáéíóúÁÉÍÓÚÄëËïÏöÖüÜñÑ ]*$";

    private TextValidator() { }

    public static void validateAllowedValue(String value, String[] allowedValues, String message) {
        for (String allowed : allowedValues) {
            if (allowed.equalsIgnoreCase(value)) {
                return;
            }
        }
        throw new IllegalArgumentException(message);
    }

    public static boolean isStringEmpty(String string) {
        return isStringNull(string) || ConstantText.EMPTY.equals(applyTrim(string));
    }

    public static boolean isStringNull(String string) {
        return ObjectValidator.isNull(string);
    }

    public static String applyTrim(String string) {
        return getDefaultValue(string.trim());
    }

    public static String getDefaultValue(String string) {
        return ObjectValidator.getDefaultValue(string, ConstantText.EMPTY);
    }

    public static boolean stringAcceptsPattern(String string, String pattern) {
        return applyTrim(string).matches(pattern);
    }

    public static boolean isStringWithLettersAndSpacesPattern(String string) {
        return stringAcceptsPattern(string, LETTERS_AND_SPACES_PATTERN);
    }
}