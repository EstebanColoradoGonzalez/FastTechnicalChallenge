package co.com.ceiba.reto.common.domain.validator;

import co.com.ceiba.reto.common.domain.constant.ConstantText;

public class TextValidator {
    private TextValidator() { }

    public static void validateAllowedValue(String value, String[] allowedValues, String message) {
        for (String allowed : allowedValues) {
            if (allowed.equalsIgnoreCase(value)) {
                return;
            }
        }
        throw new IllegalArgumentException(message);
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
}