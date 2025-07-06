package co.com.ceiba.reto.common.domain.validator;

import co.com.ceiba.reto.common.domain.constant.ConstantNumber;
import co.com.ceiba.reto.common.domain.constant.ConstantText;

public class NumberValidator {
    private NumberValidator() { }

    public static <T extends Number> void validateNumberBetween(T number, T min, T max, String message) {
        if (ObjectValidator.isNull(number) || !isNumberBetween(number, min, max)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static <T extends Number> void validateNumberGreaterThanOrEqual(T number, T comparatorNumber, String message) {
        if (!isNumberGreaterThanOrEqual(number, comparatorNumber)) {
            throw new IllegalStateException(message);
        }
    }

    public static <T extends Number> boolean isNumberBetween(T number, T lowerLimit, T upperLimit) {
        return isNumberGreaterThanOrEqual(number, lowerLimit) && isNumberLessThanOrEqual(number, upperLimit);
    }

    public static <T extends Number> boolean isNumberGreaterThanOrEqual(T number, T comparatorNumber) {
        return isNumberGreater(number, comparatorNumber) || isNumberEqual(number, comparatorNumber);
    }

    public static <T extends Number> boolean isNumberLessThanOrEqual(T number, T comparatorNumber) {
        return isNumberLess(number, comparatorNumber) || isNumberEqual(number, comparatorNumber);
    }

    public static <T extends Number> boolean isNumberGreater(T number, T comparatorNumber) {
        return getDefaultNumber(number).doubleValue() > getDefaultNumber(comparatorNumber).doubleValue();
    }

    public static <T extends Number> boolean isNumberLess(T number, T comparatorNumber) {
        return getDefaultNumber(number).doubleValue() < getDefaultNumber(comparatorNumber).doubleValue();
    }

    public static <T extends Number> boolean isNumberEqual(T number, T comparatorNumber) {
        return getDefaultNumber(number).doubleValue() == getDefaultNumber(comparatorNumber).doubleValue();
    }

    @SuppressWarnings(ConstantText.UNCHECKED)
    public static <T extends Number> T getDefaultNumber(T number) {
        return (T) ObjectValidator.getDefaultValue(number, ConstantNumber.ZERO);
    }
}