package co.com.ceiba.reto.module.domain.model;

import co.com.ceiba.reto.common.domain.constant.ConstantMessage;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class OrderItemTest {

    @Test
    void buildShouldCreateOrderItemWhenValidParameters() {
        var productId = 1;
        var quantity = 5;
        var unitPrice = BigDecimal.valueOf(150.00);

        var orderItem = OrderItem.build(productId, quantity, unitPrice);

        assertNotNull(orderItem);
        assertNull(orderItem.getId());
        assertEquals(productId, orderItem.getProductId());
        assertEquals(quantity, orderItem.getQuantity());
        assertEquals(unitPrice, orderItem.getUnitPrice());
        assertEquals(BigDecimal.valueOf(750.00), orderItem.getTotal());
    }

    @Test
    void buildWithIdShouldCreateOrderItemWithId() {
        var id = 10L;
        var productId = 2;
        var quantity = 3;
        var unitPrice = BigDecimal.valueOf(50.00);

        var orderItem = OrderItem.buildWithId(id, productId, quantity, unitPrice);

        assertNotNull(orderItem);
        assertEquals(id, orderItem.getId());
        assertEquals(productId, orderItem.getProductId());
        assertEquals(quantity, orderItem.getQuantity());
        assertEquals(unitPrice, orderItem.getUnitPrice());
        assertEquals(BigDecimal.valueOf(150.00), orderItem.getTotal());
    }

    @Test
    void buildShouldThrowExceptionWhenProductIdIsNull() {
        var quantity = 5;
        var unitPrice = BigDecimal.valueOf(100.0);

        var exception = assertThrows(IllegalArgumentException.class, () ->
                OrderItem.build(null, quantity, unitPrice));
        assertEquals(ConstantMessage.PRODUCT_ID_IS_MANDATORY, exception.getMessage());
    }

    @Test
    void buildShouldThrowExceptionWhenQuantityIsNull() {
        var productId = 1;
        var unitPrice = BigDecimal.valueOf(100.0);

        var exception = assertThrows(IllegalArgumentException.class, () ->
                OrderItem.build(productId, null, unitPrice));
        assertEquals(ConstantMessage.QUANTITY_IS_MANDATORY, exception.getMessage());
    }

    @Test
    void buildShouldThrowExceptionWhenUnitPriceIsNull() {
        var productId = 1;
        var quantity = 3;

        var exception = assertThrows(IllegalArgumentException.class, () ->
                OrderItem.build(productId, quantity, null));
        assertEquals(ConstantMessage.UNIT_PRICE_IS_MANDATORY, exception.getMessage());
    }

    @Test
    void buildShouldThrowExceptionWhenQuantityIsLessThanOne() {
        var productId = 1;
        var quantity = 0;
        var unitPrice = BigDecimal.valueOf(100.0);

        var exception = assertThrows(IllegalArgumentException.class, () ->
                OrderItem.build(productId, quantity, unitPrice));
        assertEquals(ConstantMessage.INVALID_ITEM_QUANTITY, exception.getMessage());
    }

    @Test
    void buildShouldThrowExceptionWhenQuantityIsGreaterThanTen() {
        var productId = 1;
        var quantity = 11;
        var unitPrice = BigDecimal.valueOf(100.0);

        var exception = assertThrows(IllegalArgumentException.class, () ->
                OrderItem.build(productId, quantity, unitPrice));
        assertEquals(ConstantMessage.INVALID_ITEM_QUANTITY, exception.getMessage());
    }
}