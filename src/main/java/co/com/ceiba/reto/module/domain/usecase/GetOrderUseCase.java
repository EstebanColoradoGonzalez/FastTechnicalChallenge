package co.com.ceiba.reto.module.domain.usecase;

import co.com.ceiba.reto.common.domain.exception.InvalidValueException;
import co.com.ceiba.reto.common.domain.constant.ConstantMessage;
import co.com.ceiba.reto.common.domain.exception.NotFoundException;
import co.com.ceiba.reto.module.domain.model.Order;
import co.com.ceiba.reto.module.domain.port.query.OrderQueryRepository;

import java.util.Optional;

public class GetOrderUseCase {

    private final OrderQueryRepository orderQueryRepository;

    public GetOrderUseCase(OrderQueryRepository orderQueryRepository) {
        this.orderQueryRepository = orderQueryRepository;
    }

    public Order execute(Long orderId) {
        Optional<Order> orderOpt = orderQueryRepository.findById(orderId);

        if (orderOpt.isEmpty()) {
            throw new NotFoundException(ConstantMessage.ORDER_NOT_FOUND + orderId);
        }

        return orderOpt.get();
    }
}
