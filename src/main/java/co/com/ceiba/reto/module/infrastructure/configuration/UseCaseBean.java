package co.com.ceiba.reto.module.infrastructure.configuration;

import co.com.ceiba.reto.module.domain.service.PaymentService;
import co.com.ceiba.reto.module.domain.service.ReserveStockService;
import co.com.ceiba.reto.module.domain.service.SaveOrderItemsService;
import co.com.ceiba.reto.module.domain.service.SaveOrderService;
import co.com.ceiba.reto.module.domain.service.VerifyStockService;
import co.com.ceiba.reto.module.domain.usecase.CreateOrderUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseBean {

    @Bean
    public CreateOrderUseCase createOrderUseCase(
            VerifyStockService verifyStockService,
            PaymentService paymentService,
            ReserveStockService reserveStockService,
            SaveOrderService saveOrderService,
            SaveOrderItemsService saveOrderItemsService
    ) {
        return new CreateOrderUseCase(
                verifyStockService,
                paymentService,
                reserveStockService,
                saveOrderService,
                saveOrderItemsService
        );
    }
}