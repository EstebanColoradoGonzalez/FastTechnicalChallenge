package co.com.ceiba.reto.module.domain.testdatabuilder;

import co.com.ceiba.reto.module.domain.model.PaymentMethod;

public class PaymentMethodTestDataBuilder {

    private String value;

    public PaymentMethodTestDataBuilder() {
        this.value = PaymentMethod.CREDIT_CARD.toString();
    }

    public PaymentMethodTestDataBuilder withValue(String value) {
        this.value = value;
        return this;
    }

    public PaymentMethod build() {
        return PaymentMethod.fromString(value);
    }
}
