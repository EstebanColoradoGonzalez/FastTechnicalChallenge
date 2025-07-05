package co.com.ceiba.reto.common.domain.validator;

public class ObjectValidator {
    private ObjectValidator() { }

    public static <T> boolean isNull(T object) {
        return object == null;
    }

    public static <T> T getDefaultValue(T object, T defaultValue) {
        return isNull(object) ? defaultValue : object;
    }
}