package co.com.ceiba.reto.module.domain.service;

import co.com.ceiba.reto.common.domain.constant.ConstantMessage;
import co.com.ceiba.reto.module.domain.model.OrderItem;
import co.com.ceiba.reto.module.domain.port.command.OrderItemCommandRepository;
import co.com.ceiba.reto.module.domain.testdatabuilder.OrderItemTestDataBuilder;
import co.com.ceiba.reto.module.domain.testdatabuilder.OrderTestDataBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SaveOrderItemsServiceTest {

    @Mock
    private OrderItemCommandRepository orderItemCommandRepository;

    @InjectMocks
    private SaveOrderItemsService saveOrderItemsService;

    @Test
    void executeShouldSaveAllOrderItems() {
        var item1 = new OrderItemTestDataBuilder().withProductId(1).withQuantity(2).build();
        var item2 = new OrderItemTestDataBuilder().withProductId(2).withQuantity(3).build();
        var order = new OrderTestDataBuilder().withId(10L).withItems(List.of(item1, item2)).build();

        when(orderItemCommandRepository.save(any(OrderItem.class), any(Long.class))).thenReturn(1L);

        saveOrderItemsService.execute(order);

        verify(orderItemCommandRepository, times(1)).save(item1, 10L);
        verify(orderItemCommandRepository, times(1)).save(item2, 10L);
        verify(orderItemCommandRepository, times(2)).save(any(OrderItem.class), eq(10L));
    }

    @Test
    void executeShouldThrowExceptionWhenOrderHasNoItems() {
        var exception = assertThrows(IllegalArgumentException.class, () ->
                new OrderTestDataBuilder().withItems(List.of()).build()
        );

        assertEquals(ConstantMessage.ITEMS_ARE_MANDATORY, exception.getMessage());
    }
}
