package co.com.ceiba.reto.module.domain.testdatabuilder;

import co.com.ceiba.reto.module.domain.model.OrderItem;

import java.math.BigDecimal;

public class OrderItemTestDataBuilder {

    private Long id;
    private Integer productId;
    private Integer quantity;
    private BigDecimal unitPrice;

    public OrderItemTestDataBuilder() {
        this.id = null;
        this.productId = 1;
        this.quantity = 2;
        this.unitPrice = BigDecimal.valueOf(100);
    }

    public OrderItemTestDataBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public OrderItemTestDataBuilder withProductId(Integer productId) {
        this.productId = productId;
        return this;
    }

    public OrderItemTestDataBuilder withQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public OrderItemTestDataBuilder withUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public OrderItem build() {
        if (id == null) {
            return OrderItem.build(productId, quantity, unitPrice);
        } else {
            return OrderItem.buildWithId(id, productId, quantity, unitPrice);
        }
    }
}
