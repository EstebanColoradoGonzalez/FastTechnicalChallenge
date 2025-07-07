package co.com.ceiba.reto.common.infrastructure.error;

import co.com.ceiba.reto.common.domain.constant.ConstantMessage;
import co.com.ceiba.reto.common.domain.exception.*;
import co.com.ceiba.reto.common.domain.validator.ObjectValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import java.util.concurrent.ConcurrentHashMap;

@ControllerAdvice
public class ErrorHandler {
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger(ErrorHandler.class);
    private static final ConcurrentHashMap<String, Integer> CODES_STATE = new ConcurrentHashMap<>();

    public ErrorHandler() {
        CODES_STATE.put(InvalidValueException.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
        CODES_STATE.put(NotFoundException.class.getSimpleName(), HttpStatus.NOT_FOUND.value());
        CODES_STATE.put(NullPointerException.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
        CODES_STATE.put(NoResourceFoundException.class.getSimpleName(), HttpStatus.NOT_FOUND.value());
        CODES_STATE.put(IllegalArgumentException.class.getSimpleName(), HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Error> handleAllExceptions(Exception exception) {
        ResponseEntity<Error> result;

        var nameException = exception.getClass().getSimpleName();
        var message = exception.getMessage();
        var code = CODES_STATE.get(nameException);

        if (!ObjectValidator.isNull(code)) {
            var error = new Error(nameException, message);
            result = new ResponseEntity<>(error, HttpStatus.valueOf(code));
        } else {
            LOGGER_ERROR.error(nameException, exception);
            var error = new Error(nameException, ConstantMessage.AN_ERROR_OCCURRED_PLEASE_CONTACT_THE_ADMINISTRATOR);
            result = new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return result;
    }
}