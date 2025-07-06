package co.com.ceiba.reto.module.domain.service;

import co.com.ceiba.reto.module.domain.model.Order;
import co.com.ceiba.reto.module.domain.port.command.OrderCommandRepository;

public class SaveOrderService {
    private final OrderCommandRepository orderCommandRepository;

    public SaveOrderService(OrderCommandRepository orderCommandRepository) {
        this.orderCommandRepository = orderCommandRepository;
    }

    public Long execute(Order order) {
        return orderCommandRepository.save(order);
    }
}