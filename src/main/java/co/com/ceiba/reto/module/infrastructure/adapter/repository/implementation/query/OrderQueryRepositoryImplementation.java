package co.com.ceiba.reto.module.infrastructure.adapter.repository.implementation.query;

import co.com.ceiba.reto.module.domain.model.Order;
import co.com.ceiba.reto.module.domain.port.query.OrderQueryRepository;
import co.com.ceiba.reto.module.infrastructure.adapter.mapper.OrderMapper;
import co.com.ceiba.reto.module.infrastructure.adapter.repository.dao.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OrderQueryRepositoryImplementation implements OrderQueryRepository {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Optional<Order> findById(Long orderId) {
        return orderDAO.findById(orderId)
                .map(orderMapper::buildModel);
    }
}