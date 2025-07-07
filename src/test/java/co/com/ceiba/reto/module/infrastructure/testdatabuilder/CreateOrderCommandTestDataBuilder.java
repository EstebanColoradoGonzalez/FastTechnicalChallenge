package co.com.ceiba.reto.module.infrastructure.testdatabuilder;

import co.com.ceiba.reto.module.application.command.CreateOrderCommand;
import co.com.ceiba.reto.module.application.command.CreateOrderItemCommand;
import java.util.List;

public class CreateOrderCommandTestDataBuilder {

    private Integer customerId;
    private List<CreateOrderItemCommand> items;
    private String paymentMethod;

    public CreateOrderCommandTestDataBuilder() {
        this.customerId = 1234;
        this.items = List.of(new CreateOrderItemCommandTestDataBuilder().build());
        this.paymentMethod = "CREDIT_CARD";
    }

    public CreateOrderCommandTestDataBuilder withCustomerId(Integer customerId) {
        this.customerId = customerId;
        return this;
    }

    public CreateOrderCommandTestDataBuilder withItems(List<CreateOrderItemCommand> items) {
        this.items = items;
        return this;
    }

    public CreateOrderCommandTestDataBuilder withPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public CreateOrderCommand build() {
        return new CreateOrderCommand(customerId, items, paymentMethod);
    }
}