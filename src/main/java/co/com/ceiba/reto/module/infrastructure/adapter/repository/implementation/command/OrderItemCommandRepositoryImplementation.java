package co.com.ceiba.reto.module.infrastructure.adapter.repository.implementation.command;

import co.com.ceiba.reto.common.domain.validator.ObjectValidator;
import co.com.ceiba.reto.module.domain.model.OrderItem;
import co.com.ceiba.reto.module.domain.port.command.OrderItemCommandRepository;
import co.com.ceiba.reto.module.infrastructure.adapter.mapper.OrderItemMapper;
import co.com.ceiba.reto.module.infrastructure.adapter.repository.dao.OrderDAO;
import co.com.ceiba.reto.module.infrastructure.adapter.repository.dao.OrderItemDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderItemCommandRepositoryImplementation implements OrderItemCommandRepository {

    @Autowired
    private OrderItemDAO orderItemDAO;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderDAO orderDAO;

    @Override
    public Long save(OrderItem orderItem, Long orderId) {
        var orderEntity = orderDAO.findById(orderId).orElse(null);

        assert !ObjectValidator.isNull(orderEntity);
        var entity = orderItemMapper.buildEntity(orderItem, orderEntity);
        var savedEntity = orderItemDAO.save(entity);
        return savedEntity.getId();
    }
}