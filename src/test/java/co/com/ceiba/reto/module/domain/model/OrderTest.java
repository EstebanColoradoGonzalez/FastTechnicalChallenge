package co.com.ceiba.reto.module.domain.model;

import co.com.ceiba.reto.common.domain.constant.ConstantMessage;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void buildShouldCreateOrderWhenValidParameters() {
        var customerId = 1;
        var item1 = OrderItem.build(10, 2, BigDecimal.valueOf(100));
        var item2 = OrderItem.build(20, 1, BigDecimal.valueOf(50));
        var items = List.of(item1, item2);
        var paymentMethod = PaymentMethod.CREDIT_CARD;

        var order = Order.build(customerId, items, paymentMethod);

        assertNotNull(order);
        assertNull(order.getId());
        assertEquals(customerId, order.getCustomerId());
        assertEquals(items, order.getItems());
        assertEquals(paymentMethod, order.getPaymentMethod());
        assertEquals(OrderStatus.PAID, order.getStatus());
        assertEquals(BigDecimal.valueOf(250), order.getTotalAmount());
    }

    @Test
    void buildWithStatusShouldCreateOrderWithStatusWhenValidParameters() {
        var customerId = 2;
        var item = OrderItem.build(15, 3, BigDecimal.valueOf(30));
        var items = List.of(item);
        var paymentMethod = PaymentMethod.CREDIT_CARD;
        var status = OrderStatus.FAILED;

        var order = Order.buildWithStatus(customerId, items, paymentMethod, status);

        assertNotNull(order);
        assertNull(order.getId());
        assertEquals(customerId, order.getCustomerId());
        assertEquals(items, order.getItems());
        assertEquals(paymentMethod, order.getPaymentMethod());
        assertEquals(status, order.getStatus());
        assertEquals(BigDecimal.valueOf(90), order.getTotalAmount());
    }

    @Test
    void buildWithIdShouldCreateOrderWithIdWhenValidParameters() {
        var id = 100L;
        var customerId = 3;
        var item = OrderItem.build(5, 4, BigDecimal.valueOf(25));
        var items = List.of(item);
        var paymentMethod = PaymentMethod.CREDIT_CARD;
        var status = OrderStatus.PAID;

        var order = Order.buildWithId(id, customerId, items, paymentMethod, status);

        assertNotNull(order);
        assertEquals(id, order.getId());
        assertEquals(customerId, order.getCustomerId());
        assertEquals(items, order.getItems());
        assertEquals(paymentMethod, order.getPaymentMethod());
        assertEquals(status, order.getStatus());
        assertEquals(BigDecimal.valueOf(100), order.getTotalAmount());
    }

    @Test
    void setCustomerIdShouldThrowExceptionWhenCustomerIdIsNull() {
        var item = OrderItem.build(1, 1, BigDecimal.valueOf(10));
        var items = List.of(item);
        var paymentMethod = PaymentMethod.CREDIT_CARD;

        var exception = assertThrows(IllegalArgumentException.class, () -> {
            Order.build(null, items, paymentMethod);
        });
        assertEquals(ConstantMessage.CUSTOMER_ID_IS_MANDATORY, exception.getMessage());
    }

    @Test
    void setItemsShouldThrowExceptionWhenItemsIsNull() {
        var customerId = 1;
        var paymentMethod = PaymentMethod.CREDIT_CARD;

        var exception = assertThrows(IllegalArgumentException.class, () -> {
            Order.build(customerId, null, paymentMethod);
        });
        assertEquals(ConstantMessage.ITEMS_ARE_MANDATORY, exception.getMessage());
    }

    @Test
    void setItemsShouldThrowExceptionWhenItemsIsEmpty() {
        var customerId = 1;
        var items = List.of();
        var paymentMethod = PaymentMethod.CREDIT_CARD;

        var exception = assertThrows(IllegalArgumentException.class, () -> {
            Order.build(customerId, List.of(), paymentMethod);
        });
        assertEquals(ConstantMessage.ITEMS_ARE_MANDATORY, exception.getMessage());
    }

    @Test
    void setPaymentMethodShouldThrowExceptionWhenPaymentMethodIsNull() {
        var customerId = 1;
        var item = OrderItem.build(1, 1, BigDecimal.valueOf(10));
        var items = List.of(item);

        var exception = assertThrows(IllegalArgumentException.class, () -> {
            Order.build(customerId, items, null);
        });
        assertEquals(ConstantMessage.PAYMENT_METHOD_IS_MANDATORY, exception.getMessage());
    }

    @Test
    void setStatusShouldThrowExceptionWhenStatusIsNull() {
        var customerId = 1;
        var item = OrderItem.build(1, 1, BigDecimal.valueOf(10));
        var items = List.of(item);
        var paymentMethod = PaymentMethod.CREDIT_CARD;
        var order = Order.build(customerId, items, paymentMethod);

        var exception = assertThrows(IllegalArgumentException.class, () -> {
            order.setStatus(null);
        });

        assertEquals(ConstantMessage.STATUS_IS_MANDATORY, exception.getMessage());
    }

    @Test
    void setIdShouldSetIdWhenValidId() {
        var customerId = 1;
        var item = OrderItem.build(1, 1, BigDecimal.valueOf(10));
        var items = List.of(item);
        var paymentMethod = PaymentMethod.CREDIT_CARD;
        var order = Order.build(customerId, items, paymentMethod);

        order.setId(123L);

        assertEquals(123L, order.getId());
    }
}