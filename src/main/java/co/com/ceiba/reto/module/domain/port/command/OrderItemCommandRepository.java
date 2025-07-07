package co.com.ceiba.reto.module.domain.port.command;

import co.com.ceiba.reto.module.domain.model.OrderItem;

public interface OrderItemCommandRepository {
    /**
     * Guarda o actualiza un ítem de orden.
     * @param orderItem ítem a guardar o actualizar
     * @param orderId id para relacionar con la orden
     */
    Long save(OrderItem orderItem, Long orderId);
}