package co.com.ceiba.reto.module.domain.port.command;

import co.com.ceiba.reto.module.domain.model.Order;

public interface OrderCommandRepository {
    /**
     * Guarda una nueva orden o actualiza una existente.
     * @param order la orden a guardar o actualizar
     * @return el id de la orden guardada o actualizada
     */
    Long save(Order order);

    /**
     * Actualiza el estado de una orden existente.
     * @param orderId id de la orden
     * @param status nuevo estado
     */
    Long updateStatus(Long orderId, String status);
}