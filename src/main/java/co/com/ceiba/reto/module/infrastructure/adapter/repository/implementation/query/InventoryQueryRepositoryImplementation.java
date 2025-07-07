package co.com.ceiba.reto.module.infrastructure.adapter.repository.implementation.query;

import co.com.ceiba.reto.module.domain.model.Inventory;
import co.com.ceiba.reto.module.domain.port.query.InventoryQueryRepository;
import co.com.ceiba.reto.module.infrastructure.adapter.mapper.InventoryMapper;
import co.com.ceiba.reto.module.infrastructure.adapter.repository.dao.InventoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InventoryQueryRepositoryImplementation implements InventoryQueryRepository {
    @Autowired
    private InventoryDAO inventoryDAO;

    @Autowired
    private InventoryMapper inventoryMapper;

    @Override
    public Optional<Inventory> findByProductId(Integer productId) {
        return inventoryDAO.findById(productId)
                .map(inventoryMapper::buildModel);
    }
}