package co.com.ceiba.reto.module.application.command.handler;

import co.com.ceiba.reto.common.application.handler.CommandResponseHandler;
import co.com.ceiba.reto.module.application.command.CreateOrderCommand;
import co.com.ceiba.reto.module.application.command.factory.CreateOrderFactory;
import co.com.ceiba.reto.module.application.dto.OrderDTO;
import co.com.ceiba.reto.module.domain.usecase.CreateOrderUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreateOrderHandler implements CommandResponseHandler<CreateOrderCommand, OrderDTO> {
    private final CreateOrderUseCase createOrderUseCase;
    private final CreateOrderFactory createOrderFactory;

    @Override
    public OrderDTO execute(CreateOrderCommand command) {
        var order = createOrderFactory.build(command);
        var orderCreated = createOrderUseCase.execute(order);
        return createOrderFactory.toDTO(orderCreated);
    }
}
