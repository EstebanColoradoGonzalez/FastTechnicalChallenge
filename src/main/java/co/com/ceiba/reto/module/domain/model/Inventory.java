package co.com.ceiba.reto.module.domain.model;

import co.com.ceiba.reto.common.domain.constant.ConstantMessage;
import co.com.ceiba.reto.common.domain.validator.NumberValidator;
import co.com.ceiba.reto.common.domain.validator.ObjectValidator;
import java.math.BigDecimal;

public class Inventory {
    private Integer productId;
    private Integer stock;
    private BigDecimal unitPrice;

    private Inventory(Integer productId, Integer stock, BigDecimal unitPrice) {
        setProductId(productId);
        setStock(stock);
        setUnitPrice(unitPrice);
    }

    public static Inventory build(Integer productId, Integer stock, BigDecimal unitPrice) {
        return new Inventory(productId, stock, unitPrice);
    }

    public Integer getProductId() {
        return productId;
    }

    private void setProductId(Integer productId) {
        ObjectValidator.validateMandatory(productId, ConstantMessage.PRODUCT_ID_IS_MANDATORY);
        this.productId = productId;
    }

    public Integer getStock() {
        return stock;
    }

    private void setStock(Integer stock) {
        ObjectValidator.validateMandatory(stock, ConstantMessage.STOCK_IS_MANDATORY);
        this.stock = stock;
    }

    public void decreaseStock(Integer quantity) {
        NumberValidator.validateNumberGreaterThanOrEqual(stock, quantity, ConstantMessage.NOT_ENOUGH_STOCK);

        this.stock -= quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    private void setUnitPrice(BigDecimal unitPrice) {
        ObjectValidator.validateMandatory(unitPrice, ConstantMessage.UNIT_PRICE_IS_MANDATORY);
        this.unitPrice = unitPrice;
    }
}