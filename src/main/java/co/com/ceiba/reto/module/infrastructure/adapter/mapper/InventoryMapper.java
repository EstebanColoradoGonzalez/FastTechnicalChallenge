package co.com.ceiba.reto.module.infrastructure.adapter.mapper;

import co.com.ceiba.reto.module.domain.model.Inventory;
import co.com.ceiba.reto.module.infrastructure.adapter.entity.InventoryEntity;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper {

    public InventoryEntity buildEntity(Inventory inventory) {
        return new InventoryEntity(
                inventory.getProductId(),
                inventory.getStock(),
                inventory.getUnitPrice()
        );
    }

    public void buildUpdateEntity(InventoryEntity entity, Inventory inventory) {
        entity.setStock(inventory.getStock());
        entity.setUnitPrice(inventory.getUnitPrice());
    }

    public Inventory buildModel(InventoryEntity entity) {
        return new Inventory(
                entity.getProductId(),
                entity.getStock(),
                entity.getUnitPrice()
        );
    }
}