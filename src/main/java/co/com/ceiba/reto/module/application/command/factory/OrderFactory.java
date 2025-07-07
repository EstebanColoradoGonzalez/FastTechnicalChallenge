package co.com.ceiba.reto.module.application.command.factory;

import co.com.ceiba.reto.common.domain.exception.InvalidValueException;
import co.com.ceiba.reto.common.domain.constant.ConstantMessage;
import co.com.ceiba.reto.module.application.command.CreateOrderCommand;
import co.com.ceiba.reto.module.application.command.CreateOrderItemCommand;
import co.com.ceiba.reto.module.application.dto.OrderDTO;
import co.com.ceiba.reto.module.application.dto.OrderItemDTO;
import co.com.ceiba.reto.module.domain.model.Inventory;
import co.com.ceiba.reto.module.domain.model.Order;
import co.com.ceiba.reto.module.domain.model.OrderItem;
import co.com.ceiba.reto.module.domain.model.PaymentMethod;
import co.com.ceiba.reto.module.domain.port.query.InventoryQueryRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderFactory {
    private final InventoryQueryRepository inventoryQueryRepository;

    public Order build(CreateOrderCommand command) {
        List<OrderItem> items = command.getItems()
                .stream()
                .map(this::buildOrderItem)
                .collect(Collectors.toList());

        PaymentMethod paymentMethod = PaymentMethod.fromString(command.getPaymentMethod());

        return Order.build(command.getCustomerId(), items, paymentMethod);
    }

    private OrderItem buildOrderItem(CreateOrderItemCommand itemCommand) {
        Inventory inventory = inventoryQueryRepository.findByProductId(itemCommand.getProductId())
                .orElseThrow(() -> new InvalidValueException(
                        ConstantMessage.PRODUCT_NOT_FOUND + itemCommand.getProductId()));

        return OrderItem.build(itemCommand.getProductId(), itemCommand.getQuantity(), inventory.getUnitPrice());
    }

    public OrderDTO toDTO(Order order) {
        List<OrderItemDTO> items = order.getItems()
                .stream()
                .map(item -> new OrderItemDTO(item.getProductId(), item.getQuantity()))
                .collect(Collectors.toList());

        return new OrderDTO(
                order.getId(),
                items,
                order.getTotalAmount(),
                order.getStatus().name()
        );
    }
}