package co.com.ceiba.reto.module.domain.model;

import co.com.ceiba.reto.common.domain.constant.ConstantMessage;
import co.com.ceiba.reto.common.domain.constant.ConstantNumber;
import co.com.ceiba.reto.common.domain.validator.NumberValidator;
import co.com.ceiba.reto.common.domain.validator.ObjectValidator;

import java.math.BigDecimal;

public class OrderItem {
    private Long id;
    private Integer productId;
    private Integer quantity;
    private BigDecimal unitPrice;

    private OrderItem(Long id, Integer productId, Integer quantity, BigDecimal unitPrice) {
        setId(id);
        setProductId(productId);
        setQuantity(quantity);
        setUnitPrice(unitPrice);
    }

    public static OrderItem build(Integer productId, Integer quantity, BigDecimal unitPrice) {
        return new OrderItem(null, productId, quantity, unitPrice);
    }

    public static OrderItem buildWithId(Long id, Integer productId, Integer quantity, BigDecimal unitPrice) {
        return new OrderItem(id, productId, quantity, unitPrice);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    private void setProductId(Integer productId) {
        ObjectValidator.validateMandatory(productId, ConstantMessage.PRODUCT_ID_IS_MANDATORY);
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    private void setQuantity(Integer quantity) {
        ObjectValidator.validateMandatory(quantity, ConstantMessage.QUANTITY_IS_MANDATORY);
        NumberValidator.validateNumberBetween(quantity, ConstantNumber.ONE, ConstantNumber.TEN, ConstantMessage.INVALID_ITEM_QUANTITY);

        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    private void setUnitPrice(BigDecimal unitPrice) {
        ObjectValidator.validateMandatory(unitPrice, ConstantMessage.UNIT_PRICE_IS_MANDATORY);

        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}