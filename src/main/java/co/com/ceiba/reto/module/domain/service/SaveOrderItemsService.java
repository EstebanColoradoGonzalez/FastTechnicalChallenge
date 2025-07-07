package co.com.ceiba.reto.module.domain.service;

import co.com.ceiba.reto.module.domain.model.Order;
import co.com.ceiba.reto.module.domain.model.OrderItem;
import co.com.ceiba.reto.module.domain.port.command.OrderItemCommandRepository;

public class SaveOrderItemsService {
    private final OrderItemCommandRepository orderItemCommandRepository;

    public SaveOrderItemsService(OrderItemCommandRepository orderItemCommandRepository) {
        this.orderItemCommandRepository = orderItemCommandRepository;
    }

    public void execute(Order order) {
        for (OrderItem item : order.getItems()) {
            orderItemCommandRepository.save(item, order.getId());
        }
    }
}