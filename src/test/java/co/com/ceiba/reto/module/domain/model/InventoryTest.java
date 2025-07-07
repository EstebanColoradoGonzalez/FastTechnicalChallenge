package co.com.ceiba.reto.module.domain.model;

import co.com.ceiba.reto.common.domain.constant.ConstantMessage;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    @Test
    void buildShouldCreateInventoryWhenValidParameters() {
        var productId = 1;
        var stock = 10;
        var unitPrice = BigDecimal.valueOf(100.0);

        var inventory = Inventory.build(productId, stock, unitPrice);

        assertNotNull(inventory);
        assertEquals(productId, inventory.getProductId());
        assertEquals(stock, inventory.getStock());
        assertEquals(unitPrice, inventory.getUnitPrice());
    }

    @Test
    void buildShouldThrowExceptionWhenProductIdIsNull() {
        var stock = 10;
        var unitPrice = BigDecimal.valueOf(100.0);

        var exception = assertThrows(IllegalArgumentException.class, () ->
                Inventory.build(null, stock, unitPrice));

        assertEquals(ConstantMessage.PRODUCT_ID_IS_MANDATORY, exception.getMessage());
    }

    @Test
    void buildShouldThrowExceptionWhenStockIsNull() {
        var productId = 1;
        var unitPrice = BigDecimal.valueOf(100.0);

        var exception = assertThrows(IllegalArgumentException.class, () ->
                Inventory.build(productId, null, unitPrice));
        assertEquals(ConstantMessage.STOCK_IS_MANDATORY, exception.getMessage());
    }

    @Test
    void buildShouldThrowExceptionWhenUnitPriceIsNull() {
        var productId = 1;
        var stock = 10;

        var exception = assertThrows(IllegalArgumentException.class, () ->
                Inventory.build(productId, stock, null));
        assertEquals(ConstantMessage.UNIT_PRICE_IS_MANDATORY, exception.getMessage());
    }

    @Test
    void decreaseStockShouldDecreaseStockWhenQuantityIsValid() {
        var inventory = Inventory.build(1, 10, BigDecimal.valueOf(100.0));
        var quantityToDecrease = 5;

        inventory.decreaseStock(quantityToDecrease);

        assertEquals(5, inventory.getStock());
    }

    @Test
    void decreaseStockShouldThrowExceptionWhenQuantityIsGreaterThanStock() {
        var inventory = Inventory.build(1, 10, BigDecimal.valueOf(100.0));
        var quantityToDecrease = 15;

        var exception = assertThrows(IllegalStateException.class, () ->
                inventory.decreaseStock(quantityToDecrease));
        assertEquals(ConstantMessage.NOT_ENOUGH_STOCK, exception.getMessage());
    }
}
