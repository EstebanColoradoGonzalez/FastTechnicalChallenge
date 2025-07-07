package co.com.ceiba.reto.module.domain.service;

import co.com.ceiba.reto.common.domain.constant.ConstantMessage;
import co.com.ceiba.reto.common.domain.exception.InvalidValueException;
import co.com.ceiba.reto.module.domain.port.command.InventoryCommandRepository;
import co.com.ceiba.reto.module.domain.port.query.InventoryQueryRepository;
import co.com.ceiba.reto.module.domain.testdatabuilder.InventoryTestDataBuilder;
import co.com.ceiba.reto.module.domain.testdatabuilder.OrderItemTestDataBuilder;
import co.com.ceiba.reto.module.domain.testdatabuilder.OrderTestDataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReserveStockServiceTest {

    @Mock
    private InventoryQueryRepository inventoryQueryRepository;

    @Mock
    private InventoryCommandRepository inventoryCommandRepository;

    @InjectMocks
    private ReserveStockService reserveStockService;

    @Test
    void executeShouldReserveStockSuccessfully() {
        var orderItem1 = new OrderItemTestDataBuilder().withProductId(1).withQuantity(2).build();
        var orderItem2 = new OrderItemTestDataBuilder().withProductId(2).withQuantity(3).build();
        var order = new OrderTestDataBuilder().withItems(java.util.List.of(orderItem1, orderItem2)).build();
        var inventory1 = new InventoryTestDataBuilder().withProductId(1).withStock(10).build();
        var inventory2 = new InventoryTestDataBuilder().withProductId(2).withStock(5).build();
        when(inventoryQueryRepository.findByProductId(1)).thenReturn(Optional.of(inventory1));
        when(inventoryQueryRepository.findByProductId(2)).thenReturn(Optional.of(inventory2));
        when(inventoryCommandRepository.updateStock(any())).thenReturn(1L);

        reserveStockService.execute(order);

        verify(inventoryQueryRepository, times(1)).findByProductId(1);
        verify(inventoryQueryRepository, times(1)).findByProductId(2);
        verify(inventoryCommandRepository, times(2)).updateStock(any());
        Assertions.assertEquals(8, inventory1.getStock());
        Assertions.assertEquals(2, inventory2.getStock());
    }

    @Test
    void executeShouldThrowExceptionWhenInventoryNotFound() {
        var orderItem = new OrderItemTestDataBuilder().withProductId(1).withQuantity(2).build();
        var order = new OrderTestDataBuilder().withItems(java.util.List.of(orderItem)).build();
        when(inventoryQueryRepository.findByProductId(1)).thenReturn(Optional.empty());

        var exception = Assertions.assertThrows(InvalidValueException.class,
                () -> reserveStockService.execute(order));
        Assertions.assertEquals(ConstantMessage.PRODUCT_NOT_FOUND + "1", exception.getMessage());
        verify(inventoryQueryRepository, times(1)).findByProductId(1);
        verify(inventoryCommandRepository, never()).updateStock(any());
    }

    @Test
    void executeShouldThrowExceptionWhenStockIsInsufficient() {
        var orderItem = new OrderItemTestDataBuilder().withProductId(1).withQuantity(10).build();
        var order = new OrderTestDataBuilder().withItems(java.util.List.of(orderItem)).build();
        var inventory = new InventoryTestDataBuilder().withProductId(1).withStock(5).build();
        when(inventoryQueryRepository.findByProductId(1)).thenReturn(Optional.of(inventory));

        var exception = Assertions.assertThrows(IllegalStateException.class,
                () -> reserveStockService.execute(order));
        Assertions.assertEquals(ConstantMessage.NOT_ENOUGH_STOCK, exception.getMessage());
        verify(inventoryQueryRepository, times(1)).findByProductId(1);
        verify(inventoryCommandRepository, never()).updateStock(any());
    }
}