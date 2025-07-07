package co.com.ceiba.reto.module.infrastructure.testdatabuilder;

import co.com.ceiba.reto.module.application.command.CreateOrderItemCommand;

public class CreateOrderItemCommandTestDataBuilder {

    private Integer productId;
    private Integer quantity;

    public CreateOrderItemCommandTestDataBuilder() {
        this.productId = 456;
        this.quantity = 2;
    }

    public CreateOrderItemCommandTestDataBuilder withProductId(Integer productId) {
        this.productId = productId;
        return this;
    }

    public CreateOrderItemCommandTestDataBuilder withQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public CreateOrderItemCommand build() {
        return new CreateOrderItemCommand(productId, quantity);
    }
}

