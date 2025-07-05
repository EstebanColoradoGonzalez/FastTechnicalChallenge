package co.com.ceiba.reto.common.domain.exception;

import co.com.ceiba.reto.common.domain.constant.ConstantNumber;

import java.io.Serial;

public class PatternException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = ConstantNumber.ONE_LONG;

    public PatternException(String message) {
        super(message);
    }
}
