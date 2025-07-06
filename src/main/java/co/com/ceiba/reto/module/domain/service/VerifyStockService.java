package co.com.ceiba.reto.module.domain.service;

import co.com.ceiba.reto.common.domain.exception.InvalidValueException;
import co.com.ceiba.reto.common.domain.constant.ConstantMessage;
import co.com.ceiba.reto.module.domain.model.Inventory;
import co.com.ceiba.reto.module.domain.model.Order;
import co.com.ceiba.reto.module.domain.model.OrderItem;
import co.com.ceiba.reto.module.domain.port.query.InventoryQueryRepository;

public class VerifyStockService {
    private final InventoryQueryRepository inventoryQueryRepository;

    public VerifyStockService(InventoryQueryRepository inventoryQueryRepository) {
        this.inventoryQueryRepository = inventoryQueryRepository;
    }

    public void execute(Order order) {
        for (OrderItem item : order.getItems()) {
            Inventory inventory = inventoryQueryRepository.findByProductId(item.getProductId())
                    .orElseThrow(() -> new InvalidValueException(ConstantMessage.PRODUCT_NOT_FOUND + item.getProductId()));
            if (inventory.getStock() < item.getQuantity()) {
                throw new InvalidValueException(ConstantMessage.INSUFFICIENT_STOCK + item.getProductId());
            }
        }
    }
}