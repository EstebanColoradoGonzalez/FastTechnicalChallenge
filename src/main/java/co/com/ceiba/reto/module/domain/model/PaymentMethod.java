package co.com.ceiba.reto.module.domain.model;

import co.com.ceiba.reto.common.domain.constant.ConstantMessage;
import co.com.ceiba.reto.common.domain.validator.TextValidator;

public enum PaymentMethod {
    CREDIT_CARD;

    public static PaymentMethod fromString(String value) {
        TextValidator.validateAllowedValue(value, new String[]{CREDIT_CARD.toString()}, ConstantMessage.PAYMENT_METHOD_NOT_ALLOWED);

        return CREDIT_CARD;
    }
}