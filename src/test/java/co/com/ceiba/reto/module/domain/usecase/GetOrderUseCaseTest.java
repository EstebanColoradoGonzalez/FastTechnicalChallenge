package co.com.ceiba.reto.module.domain.usecase;

import co.com.ceiba.reto.common.domain.constant.ConstantMessage;
import co.com.ceiba.reto.common.domain.exception.NotFoundException;
import co.com.ceiba.reto.module.domain.port.query.OrderQueryRepository;
import co.com.ceiba.reto.module.domain.testdatabuilder.OrderTestDataBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetOrderUseCaseTest {

    @Mock
    private OrderQueryRepository orderQueryRepository;

    @InjectMocks
    private GetOrderUseCase getOrderUseCase;

    @Test
    void executeShouldReturnOrderWhenFound() {
        var orderId = 1L;
        var order = new OrderTestDataBuilder().withId(orderId).build();
        when(orderQueryRepository.findById(orderId)).thenReturn(Optional.of(order));

        var result = getOrderUseCase.execute(orderId);

        verify(orderQueryRepository, times(1)).findById(orderId);
        assertNotNull(result);
        assertEquals(orderId, result.getId());
    }

    @Test
    void executeShouldThrowNotFoundExceptionWhenOrderNotFound() {
        var orderId = 2L;

        when(orderQueryRepository.findById(orderId)).thenReturn(Optional.empty());

        var exception = assertThrows(NotFoundException.class, () -> getOrderUseCase.execute(orderId));
        assertEquals(ConstantMessage.ORDER_NOT_FOUND + orderId, exception.getMessage());
        verify(orderQueryRepository, times(1)).findById(orderId);
    }
}