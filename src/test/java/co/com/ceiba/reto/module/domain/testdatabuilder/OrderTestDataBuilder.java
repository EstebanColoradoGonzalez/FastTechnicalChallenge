package co.com.ceiba.reto.module.domain.testdatabuilder;

import co.com.ceiba.reto.module.domain.model.Order;
import co.com.ceiba.reto.module.domain.model.OrderItem;
import co.com.ceiba.reto.module.domain.model.OrderStatus;
import co.com.ceiba.reto.module.domain.model.PaymentMethod;

import java.util.List;

public class OrderTestDataBuilder {

    private Long id;
    private Integer customerId;
    private List<OrderItem> items;
    private PaymentMethod paymentMethod;
    private OrderStatus status;

    public OrderTestDataBuilder() {
        this.id = null;
        this.customerId = 1;
        this.items = List.of(new OrderItemTestDataBuilder().build());
        this.paymentMethod = PaymentMethod.CREDIT_CARD;
        this.status = OrderStatus.PAID;
    }

    public OrderTestDataBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public OrderTestDataBuilder withCustomerId(Integer customerId) {
        this.customerId = customerId;
        return this;
    }

    public OrderTestDataBuilder withItems(List<OrderItem> items) {
        this.items = items;
        return this;
    }

    public OrderTestDataBuilder withPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public OrderTestDataBuilder withStatus(OrderStatus status) {
        this.status = status;
        return this;
    }

    public Order build() {
        if (id == null) {
            if (status == null) {
                return Order.build(customerId, items, paymentMethod);
            } else {
                return Order.buildWithStatus(customerId, items, paymentMethod, status);
            }
        } else {
            return Order.buildWithId(id, customerId, items, paymentMethod, status);
        }
    }
}
