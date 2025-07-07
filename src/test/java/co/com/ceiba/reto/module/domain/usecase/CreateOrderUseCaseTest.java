package co.com.ceiba.reto.module.domain.usecase;

import co.com.ceiba.reto.common.domain.constant.ConstantMessage;
import co.com.ceiba.reto.common.domain.exception.InvalidValueException;
import co.com.ceiba.reto.module.domain.model.Order;
import co.com.ceiba.reto.module.domain.model.OrderStatus;
import co.com.ceiba.reto.module.domain.service.PaymentService;
import co.com.ceiba.reto.module.domain.service.ReserveStockService;
import co.com.ceiba.reto.module.domain.service.SaveOrderItemsService;
import co.com.ceiba.reto.module.domain.service.SaveOrderService;
import co.com.ceiba.reto.module.domain.service.VerifyStockService;
import co.com.ceiba.reto.module.domain.testdatabuilder.OrderTestDataBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateOrderUseCaseTest {
    @Mock
    private VerifyStockService verifyStockService;

    @Mock
    private PaymentService paymentService;

    @Mock
    private ReserveStockService reserveStockService;

    @Mock
    private SaveOrderService saveOrderService;

    @Mock
    private SaveOrderItemsService saveOrderItemsService;

    @InjectMocks
    private CreateOrderUseCase createOrderUseCase;

    private Order order;

    @BeforeEach
    void setup() {
        order = new OrderTestDataBuilder().build();
    }

    @Test
    void executeShouldCompleteSuccessfullyWhenPaymentSucceedsAndStockReserved() {
        when(paymentService.execute(order)).thenReturn(true);
        when(saveOrderService.execute(order)).thenReturn(1L);

        var result = createOrderUseCase.execute(order);

        verify(verifyStockService, times(1)).execute(order);
        verify(paymentService, times(1)).execute(order);
        verify(reserveStockService, times(1)).execute(order);
        verify(saveOrderService, times(1)).execute(order);
        verify(saveOrderItemsService, times(1)).execute(order);
        assertEquals(OrderStatus.PAID, result.getStatus());
        assertEquals(1L, result.getId());
    }

    @Test
    void executeShouldThrowExceptionAndSetFailedStatusWhenPaymentFails() {
        when(paymentService.execute(order)).thenReturn(false);
        when(saveOrderService.execute(order)).thenReturn(2L);

        var exception = assertThrows(InvalidValueException.class, () ->
                createOrderUseCase.execute(order)
        );

        assertEquals(ConstantMessage.PAYMENT_FAILED, exception.getMessage());
        verify(verifyStockService, times(1)).execute(order);
        verify(paymentService, times(1)).execute(order);
        verify(saveOrderService, times(1)).execute(order);
        verify(reserveStockService, never()).execute(order);
        verify(saveOrderItemsService, never()).execute(order);
        assertEquals(OrderStatus.FAILED, order.getStatus());
        assertEquals(2L, order.getId());
    }

    @Test
    void executeShouldThrowExceptionAndSetFailedStatusWhenReserveStockFails() {
        when(paymentService.execute(order)).thenReturn(true);
        doThrow(new RuntimeException("Stock error")).when(reserveStockService).execute(order);
        when(saveOrderService.execute(order)).thenReturn(3L);

        var exception = assertThrows(InvalidValueException.class, () ->
                createOrderUseCase.execute(order)
        );

        assertEquals(ConstantMessage.ERROR_RESERVING_STOCK + "Stock error", exception.getMessage());
        verify(verifyStockService, times(1)).execute(order);
        verify(paymentService, times(1)).execute(order);
        verify(reserveStockService, times(1)).execute(order);
        verify(saveOrderService, times(1)).execute(order);
        verify(saveOrderItemsService, never()).execute(order);
        assertEquals(OrderStatus.FAILED, order.getStatus());
        assertEquals(3L, order.getId());
    }
}