package co.com.ceiba.reto.module.domain.service;

import co.com.ceiba.reto.module.domain.port.command.OrderCommandRepository;
import co.com.ceiba.reto.module.domain.testdatabuilder.OrderTestDataBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SaveOrderServiceTest {

    @Mock
    private OrderCommandRepository orderCommandRepository;

    @InjectMocks
    private SaveOrderService saveOrderService;

    @Test
    void executeShouldSaveOrderAndReturnId() {
        var order = new OrderTestDataBuilder().build();
        var expectedId = 1L;
        when(orderCommandRepository.save(order)).thenReturn(expectedId);

        var actualId = saveOrderService.execute(order);

        verify(orderCommandRepository, times(1)).save(order);
        assertEquals(expectedId, actualId);
    }
}