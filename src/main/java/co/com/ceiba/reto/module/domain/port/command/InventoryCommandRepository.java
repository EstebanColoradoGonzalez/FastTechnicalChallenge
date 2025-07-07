package co.com.ceiba.reto.module.domain.port.command;

import co.com.ceiba.reto.module.domain.model.Inventory;

public interface InventoryCommandRepository {
    /**
     * Actualiza el stock del inventario.
     * @param inventory inventario con nuevo stock
     */
    Long updateStock(Inventory inventory);
}