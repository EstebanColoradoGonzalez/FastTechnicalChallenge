package co.com.ceiba.reto.module.application.command.handler;

import co.com.ceiba.reto.common.application.handler.CommandResponseHandler;
import co.com.ceiba.reto.module.application.command.CreateOrderCommand;
import co.com.ceiba.reto.module.application.command.factory.OrderFactory;
import co.com.ceiba.reto.module.application.dto.OrderDTO;
import co.com.ceiba.reto.module.domain.usecase.CreateOrderUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreateOrderHandler implements CommandResponseHandler<CreateOrderCommand, OrderDTO> {
    private final CreateOrderUseCase createOrderUseCase;
    private final OrderFactory orderFactory;

    @Override
    public OrderDTO execute(CreateOrderCommand command) {
        var order = orderFactory.build(command);
        var orderCreated = createOrderUseCase.execute(order);
        return orderFactory.toDTO(orderCreated);
    }
}
