package co.com.ceiba.reto.module.domain.service;

import co.com.ceiba.reto.common.domain.constant.ConstantMessage;
import co.com.ceiba.reto.common.domain.exception.InvalidValueException;
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
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VerifyStockServiceTest {

    @Mock
    private InventoryQueryRepository inventoryQueryRepository;

    @InjectMocks
    private VerifyStockService verifyStockService;

    @Test
    void executeShouldPassWhenStockIsSufficient() {
        var item1 = new OrderItemTestDataBuilder().withProductId(1).withQuantity(2).build();
        var item2 = new OrderItemTestDataBuilder().withProductId(2).withQuantity(3).build();
        var order = new OrderTestDataBuilder().withItems(List.of(item1, item2)).build();
        var inventory1 = new InventoryTestDataBuilder().withProductId(1).withStock(5).build();
        var inventory2 = new InventoryTestDataBuilder().withProductId(2).withStock(10).build();
        when(inventoryQueryRepository.findByProductId(1)).thenReturn(Optional.of(inventory1));
        when(inventoryQueryRepository.findByProductId(2)).thenReturn(Optional.of(inventory2));

        verifyStockService.execute(order);

        verify(inventoryQueryRepository, times(1)).findByProductId(1);
        verify(inventoryQueryRepository, times(1)).findByProductId(2);
    }

    @Test
    void executeShouldThrowExceptionWhenInventoryNotFound() {
        var item = new OrderItemTestDataBuilder().withProductId(1).withQuantity(2).build();
        var order = new OrderTestDataBuilder().withItems(List.of(item)).build();
        when(inventoryQueryRepository.findByProductId(1)).thenReturn(Optional.empty());

        var exception = Assertions.assertThrows(InvalidValueException.class, () ->
                verifyStockService.execute(order));
        Assertions.assertEquals(ConstantMessage.PRODUCT_NOT_FOUND + "1", exception.getMessage());
        verify(inventoryQueryRepository, times(1)).findByProductId(1);
    }

    @Test
    void executeShouldThrowExceptionWhenStockIsInsufficient() {
        var item = new OrderItemTestDataBuilder().withProductId(1).withQuantity(10).build();
        var order = new OrderTestDataBuilder().withItems(List.of(item)).build();
        var inventory = new InventoryTestDataBuilder().withProductId(1).withStock(5).build();
        when(inventoryQueryRepository.findByProductId(1)).thenReturn(Optional.of(inventory));

        var exception = Assertions.assertThrows(InvalidValueException.class, () ->
                verifyStockService.execute(order));
        Assertions.assertEquals(ConstantMessage.INSUFFICIENT_STOCK + "1", exception.getMessage());
        verify(inventoryQueryRepository, times(1)).findByProductId(1);
    }
}