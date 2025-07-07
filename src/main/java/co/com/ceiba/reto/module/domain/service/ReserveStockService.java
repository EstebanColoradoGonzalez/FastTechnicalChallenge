package co.com.ceiba.reto.module.domain.service;

import co.com.ceiba.reto.common.domain.constant.ConstantMessage;
import co.com.ceiba.reto.common.domain.exception.InvalidValueException;
import co.com.ceiba.reto.module.domain.model.Inventory;
import co.com.ceiba.reto.module.domain.model.Order;
import co.com.ceiba.reto.module.domain.model.OrderItem;
import co.com.ceiba.reto.module.domain.port.command.InventoryCommandRepository;
import co.com.ceiba.reto.module.domain.port.query.InventoryQueryRepository;

public class ReserveStockService {
    private final InventoryQueryRepository inventoryQueryRepository;
    private final InventoryCommandRepository inventoryCommandRepository;

    public ReserveStockService(InventoryQueryRepository inventoryQueryRepository,
                               InventoryCommandRepository inventoryCommandRepository) {
        this.inventoryQueryRepository = inventoryQueryRepository;
        this.inventoryCommandRepository = inventoryCommandRepository;
    }

    public void execute(Order order) {
        for (OrderItem item : order.getItems()) {
            Inventory inventory = inventoryQueryRepository.findByProductId(item.getProductId())
                    .orElseThrow(() -> new InvalidValueException(ConstantMessage.PRODUCT_NOT_FOUND + item.getProductId()));
            inventory.decreaseStock(item.getQuantity());
            inventoryCommandRepository.updateStock(inventory);
        }
    }
}