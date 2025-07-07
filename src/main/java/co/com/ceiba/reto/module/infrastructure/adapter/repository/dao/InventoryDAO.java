package co.com.ceiba.reto.module.infrastructure.adapter.repository.dao;

import co.com.ceiba.reto.module.infrastructure.adapter.entity.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryDAO extends JpaRepository<InventoryEntity, Integer> { }