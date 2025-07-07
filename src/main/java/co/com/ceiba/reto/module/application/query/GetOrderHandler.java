package co.com.ceiba.reto.module.application.query;

import co.com.ceiba.reto.common.application.handler.ParameterResponseHandler;
import co.com.ceiba.reto.module.application.command.factory.OrderFactory;
import co.com.ceiba.reto.module.application.dto.OrderDTO;
import co.com.ceiba.reto.module.domain.usecase.GetOrderUseCase;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetOrderHandler implements ParameterResponseHandler<Long, OrderDTO> {
    private final GetOrderUseCase getOrderUseCase;
    private final OrderFactory orderFactory;

    @Override
    @Transactional
    public OrderDTO execute(Long orderId) {
        return orderFactory.toDTO(getOrderUseCase.execute(orderId));
    }
}