package co.com.ceiba.reto.module.infrastructure.adapter.mapper;

import co.com.ceiba.reto.module.domain.model.Order;
import co.com.ceiba.reto.module.domain.model.OrderStatus;
import co.com.ceiba.reto.module.domain.model.OrderItem;
import co.com.ceiba.reto.module.domain.model.PaymentMethod;
import co.com.ceiba.reto.module.infrastructure.adapter.entity.OrderEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderMapper {

    private final OrderItemMapper orderItemMapper;

    public OrderMapper(OrderItemMapper orderItemMapper) {
        this.orderItemMapper = orderItemMapper;
    }

    public OrderEntity buildEntity(Order order) {
        OrderEntity entity = new OrderEntity();
        entity.setId(order.getId());
        entity.setCustomerId(order.getCustomerId());
        entity.setPaymentMethod(order.getPaymentMethod().name());
        entity.setStatus(order.getStatus().name());
        entity.setTotalAmount(order.getTotalAmount());

        if (order.getItems() != null) {
            entity.setItems(
                    order.getItems()
                            .stream()
                            .map(item -> orderItemMapper.buildEntity(item, entity))
                            .collect(Collectors.toList())
            );
        }
        return entity;
    }

    public void buildUpdateEntity(OrderEntity entity, Order order) {
        entity.setCustomerId(order.getCustomerId());
        entity.setPaymentMethod(order.getPaymentMethod().name());
        entity.setStatus(order.getStatus().name());
        entity.setTotalAmount(order.getTotalAmount());
        // Items update handled separately or via cascade
    }

    public Order buildModel(OrderEntity entity) {
        return Order.buildWithId(
                entity.getId(),
                entity.getCustomerId(),
                entity.getItems()
                        .stream()
                        .map(orderItemMapper::buildModel)
                        .collect(Collectors.toList()),
                PaymentMethod.fromString(entity.getPaymentMethod()),
                OrderStatus.valueOf(entity.getStatus())
        );
    }
}