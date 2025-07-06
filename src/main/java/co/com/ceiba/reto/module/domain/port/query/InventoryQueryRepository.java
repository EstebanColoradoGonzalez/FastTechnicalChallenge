package co.com.ceiba.reto.module.domain.port.query;

import co.com.ceiba.reto.module.domain.model.Inventory;

import java.util.Optional;

public interface InventoryQueryRepository {
    /**
     * Consulta inventario por id de producto.
     * @param productId id del producto
     * @return inventario envuelto en Optional, vacio si no existe
     */
    Optional<Inventory> findByProductId(Integer productId);
}
