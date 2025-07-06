package co.com.ceiba.reto.module.domain.model;

import co.com.ceiba.reto.common.domain.constant.ConstantMessage;
import co.com.ceiba.reto.common.domain.validator.ObjectValidator;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class Order {
    private Long id;
    private Integer customerId;
    private List<OrderItem> items;
    private PaymentMethod paymentMethod;
    private OrderStatus status;
    private BigDecimal totalAmount;

    private Order(Long id, Integer customerId, List<OrderItem> items, PaymentMethod paymentMethod, OrderStatus status) {
        setId(id);
        setCustomerId(customerId);
        setItems(items);
        setPaymentMethod(paymentMethod);
        setStatus(status);
        setTotalAmount();
    }

    public static Order build(Integer customerId, List<OrderItem> items, PaymentMethod paymentMethod) {
        return new Order(null, customerId, items, paymentMethod, OrderStatus.PAID);
    }

    public static Order buildWithStatus(Integer customerId, List<OrderItem> items, PaymentMethod paymentMethod, OrderStatus status) {
        return new Order(null, customerId, items, paymentMethod, status);
    }

    public static Order buildWithId(Long id, Integer customerId, List<OrderItem> items, PaymentMethod paymentMethod, OrderStatus status) {
        return new Order(id, customerId, items, paymentMethod, status);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    private void setCustomerId(Integer customerId) {
        ObjectValidator.validateMandatory(customerId, ConstantMessage.CUSTOMER_ID_IS_MANDATORY);

        this.customerId = customerId;
    }

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    private void setItems(List<OrderItem> items) {
        ObjectValidator.validateMandatory(items, ConstantMessage.ITEMS_ARE_MANDATORY);
        ObjectValidator.validateNotEmptyCollection(items, ConstantMessage.ITEMS_ARE_MANDATORY);

        this.items = items;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    private void setPaymentMethod(PaymentMethod paymentMethod) {
        ObjectValidator.validateMandatory(paymentMethod, ConstantMessage.PAYMENT_METHOD_IS_MANDATORY);

        this.paymentMethod = paymentMethod;
    }

    public OrderStatus getStatus() {
        return status;
    }

    private void setStatus(OrderStatus status) {
        ObjectValidator.validateMandatory(status, ConstantMessage.STATUS_IS_MANDATORY);

        this.status = status;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount() {
        this.totalAmount = calculateTotalAmount();
    }

    private BigDecimal calculateTotalAmount() {
        return items.stream()
                .map(OrderItem::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
