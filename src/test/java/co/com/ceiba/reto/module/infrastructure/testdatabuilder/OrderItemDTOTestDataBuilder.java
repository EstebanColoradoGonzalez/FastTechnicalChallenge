package co.com.ceiba.reto.module.infrastructure.testdatabuilder;

import co.com.ceiba.reto.module.application.dto.OrderItemDTO;

public class OrderItemDTOTestDataBuilder {

    private Integer productId;
    private Integer quantity;

    public OrderItemDTOTestDataBuilder() {
        this.productId = 456;
        this.quantity = 2;
    }

    public OrderItemDTOTestDataBuilder withProductId(Integer productId) {
        this.productId = productId;
        return this;
    }

    public OrderItemDTOTestDataBuilder withQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public OrderItemDTO build() {
        return new OrderItemDTO(productId, quantity);
    }
}