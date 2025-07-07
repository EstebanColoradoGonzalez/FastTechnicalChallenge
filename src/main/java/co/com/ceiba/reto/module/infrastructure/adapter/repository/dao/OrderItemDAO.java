package co.com.ceiba.reto.module.infrastructure.adapter.repository.dao;

import co.com.ceiba.reto.module.infrastructure.adapter.entity.OrderItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemDAO extends JpaRepository<OrderItemEntity, Long> { }