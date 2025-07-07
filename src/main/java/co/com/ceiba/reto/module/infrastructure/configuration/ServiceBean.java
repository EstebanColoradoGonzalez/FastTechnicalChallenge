package co.com.ceiba.reto.module.infrastructure.configuration;

import co.com.ceiba.reto.module.domain.port.command.InventoryCommandRepository;
import co.com.ceiba.reto.module.domain.port.command.OrderCommandRepository;
import co.com.ceiba.reto.module.domain.port.command.OrderItemCommandRepository;
import co.com.ceiba.reto.module.domain.port.query.InventoryQueryRepository;
import co.com.ceiba.reto.module.domain.service.PaymentService;
import co.com.ceiba.reto.module.domain.service.ReserveStockService;
import co.com.ceiba.reto.module.domain.service.SaveOrderItemsService;
import co.com.ceiba.reto.module.domain.service.SaveOrderService;
import co.com.ceiba.reto.module.domain.service.VerifyStockService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBean {

    @Bean
    public VerifyStockService verifyStockService(InventoryQueryRepository inventoryQueryRepository) {
        return new VerifyStockService(inventoryQueryRepository);
    }

    @Bean
    public PaymentService paymentService() {
        return new PaymentService();
    }

    @Bean
    public ReserveStockService reserveStockService(
            InventoryQueryRepository inventoryQueryRepository,
            InventoryCommandRepository inventoryCommandRepository
    ) {
        return new ReserveStockService(inventoryQueryRepository, inventoryCommandRepository);
    }

    @Bean
    public SaveOrderService saveOrderService(OrderCommandRepository orderCommandRepository) {
        return new SaveOrderService(orderCommandRepository);
    }

    @Bean
    public SaveOrderItemsService saveOrderItemsService(OrderItemCommandRepository orderItemCommandRepository) {
        return new SaveOrderItemsService(orderItemCommandRepository);
    }
}