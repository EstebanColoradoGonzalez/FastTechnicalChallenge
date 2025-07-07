package co.com.ceiba.reto.module.infrastructure.adapter.repository.implementation.command;

import co.com.ceiba.reto.module.domain.model.Order;
import co.com.ceiba.reto.module.domain.port.command.OrderCommandRepository;
import co.com.ceiba.reto.module.infrastructure.adapter.mapper.OrderMapper;
import co.com.ceiba.reto.module.infrastructure.adapter.repository.dao.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderCommandRepositoryImplementation implements OrderCommandRepository {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Long save(Order order) {
        var entity = orderMapper.buildEntity(order);
        var savedEntity = orderDAO.save(entity);
        return savedEntity.getId();
    }

    @Override
    public Long updateStatus(Long orderId, String status) {
        var entityOpt = orderDAO.findById(orderId);

        assert entityOpt.isPresent();
        var entity = entityOpt.get();
        entity.setStatus(status);
        orderDAO.save(entity);

        return entity.getId();
    }
}