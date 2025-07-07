package co.com.ceiba.reto.module.infrastructure.adapter.repository.implementation.command;

import co.com.ceiba.reto.module.domain.model.Inventory;
import co.com.ceiba.reto.module.domain.port.command.InventoryCommandRepository;
import co.com.ceiba.reto.module.infrastructure.adapter.mapper.InventoryMapper;
import co.com.ceiba.reto.module.infrastructure.adapter.repository.dao.InventoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InventoryCommandRepositoryImplementation implements InventoryCommandRepository {

    @Autowired
    private InventoryDAO inventoryDAO;

    @Autowired
    private InventoryMapper inventoryMapper;

    @Override
    public Long updateStock(Inventory inventory) {
        var entityOpt = inventoryDAO.findById(inventory.getProductId());

        assert entityOpt.isPresent();
        var entity = entityOpt.get();
        inventoryMapper.buildUpdateEntity(entity, inventory);

        return inventoryDAO.save(entity).getProductId().longValue();
    }
}