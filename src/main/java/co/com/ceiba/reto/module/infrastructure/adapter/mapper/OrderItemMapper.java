package co.com.ceiba.reto.module.infrastructure.adapter.mapper;

import co.com.ceiba.reto.module.domain.model.OrderItem;
import co.com.ceiba.reto.module.infrastructure.adapter.entity.OrderEntity;
import co.com.ceiba.reto.module.infrastructure.adapter.entity.OrderItemEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    public OrderItemEntity buildEntity(OrderItem model, OrderEntity orderEntity) {
        OrderItemEntity entity = new OrderItemEntity();
        entity.setId(model.getId());
        entity.setOrder(orderEntity);
        entity.setProductId(model.getProductId());
        entity.setQuantity(model.getQuantity());
        entity.setUnitPrice(model.getUnitPrice());
        return entity;
    }

    public void buildUpdateEntity(OrderItemEntity entity, OrderItem model) {
        entity.setProductId(model.getProductId());
        entity.setQuantity(model.getQuantity());
        entity.setUnitPrice(model.getUnitPrice());
    }

    public OrderItem buildModel(OrderItemEntity entity) {
        return OrderItem.buildWithId(
                entity.getId(),
                entity.getProductId(),
                entity.getQuantity(),
                entity.getUnitPrice()
        );
    }
}