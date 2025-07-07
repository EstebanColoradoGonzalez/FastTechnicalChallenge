package co.com.ceiba.reto.module.infrastructure.adapter.repository.dao;

import co.com.ceiba.reto.module.infrastructure.adapter.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDAO extends JpaRepository<OrderEntity, Long> { }