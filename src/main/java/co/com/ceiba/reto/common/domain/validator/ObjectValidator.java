package co.com.ceiba.reto.common.domain.validator;

import java.util.Collection;

public class ObjectValidator {
    private ObjectValidator() { }

    public static <T> void validateMandatory(T object, String message) {
        if (isNull(object)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static <T extends Collection<?>> void validateNotEmptyCollection(T collection, String message) {
        if (isNull(collection) || collection.isEmpty()) {
            throw new IllegalArgumentException(message);
        }
    }

    public static <T> boolean isNull(T object) {
        return object == null;
    }

    public static <T> T getDefaultValue(T object, T defaultValue) {
        return isNull(object) ? defaultValue : object;
    }
}