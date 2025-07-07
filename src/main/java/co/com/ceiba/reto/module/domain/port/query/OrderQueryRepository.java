package co.com.ceiba.reto.module.domain.port.query;

import co.com.ceiba.reto.module.domain.model.Order;

import java.util.Optional;

public interface OrderQueryRepository {
    /**
     * Consulta una orden por su id.
     * @param orderId id de la orden
     * @return orden envuelta en Optional, vacía si no existe
     */
    Optional<Order> findById(Long orderId);
}