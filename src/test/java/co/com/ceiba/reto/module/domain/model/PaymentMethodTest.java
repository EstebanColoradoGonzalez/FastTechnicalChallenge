package co.com.ceiba.reto.module.domain.model;

import co.com.ceiba.reto.common.domain.constant.ConstantMessage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PaymentMethodTest {

    @Test
    void fromStringShouldReturnCreditCardWhenInputIsValid() {
        var input = "CREDIT_CARD";

        var result = PaymentMethod.fromString(input);

        assertEquals(PaymentMethod.CREDIT_CARD, result);
    }

    @Test
    void fromStringShouldThrowExceptionWhenInputIsInvalid() {
        var input = "INVALID_METHOD";

        var exception = assertThrows(IllegalArgumentException.class, () ->
                PaymentMethod.fromString(input)
        );
        assertEquals(ConstantMessage.PAYMENT_METHOD_NOT_ALLOWED, exception.getMessage());
    }

    @Test
    void fromStringShouldThrowExceptionWhenInputIsNull() {
        var exception = assertThrows(IllegalArgumentException.class, () ->
                PaymentMethod.fromString(null)
        );
        assertEquals(ConstantMessage.PAYMENT_METHOD_NOT_ALLOWED, exception.getMessage());
    }
}
