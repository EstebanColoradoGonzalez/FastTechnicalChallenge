package co.com.ceiba.reto.module.domain.testdatabuilder;

import co.com.ceiba.reto.module.domain.model.Inventory;

import java.math.BigDecimal;

public class InventoryTestDataBuilder {

    private Integer productId;
    private Integer stock;
    private BigDecimal unitPrice;

    public InventoryTestDataBuilder() {
        this.productId = 1;
        this.stock = 100;
        this.unitPrice = BigDecimal.valueOf(50.0);
    }

    public InventoryTestDataBuilder withProductId(Integer productId) {
        this.productId = productId;
        return this;
    }

    public InventoryTestDataBuilder withStock(Integer stock) {
        this.stock = stock;
        return this;
    }

    public InventoryTestDataBuilder withUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public Inventory build() {
        return Inventory.build(productId, stock, unitPrice);
    }
}
