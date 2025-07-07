package co.com.ceiba.reto.module.domain.usecase;

import co.com.ceiba.reto.common.domain.constant.ConstantMessage;
import co.com.ceiba.reto.common.domain.exception.InvalidValueException;
import co.com.ceiba.reto.module.domain.model.Order;
import co.com.ceiba.reto.module.domain.model.OrderStatus;
import co.com.ceiba.reto.module.domain.service.PaymentService;
import co.com.ceiba.reto.module.domain.service.ReserveStockService;
import co.com.ceiba.reto.module.domain.service.SaveOrderItemsService;
import co.com.ceiba.reto.module.domain.service.SaveOrderService;
import co.com.ceiba.reto.module.domain.service.VerifyStockService;

public class CreateOrderUseCase {
    private final VerifyStockService verifyStockService;
    private final PaymentService paymentService;
    private final ReserveStockService reserveStockService;
    private final SaveOrderService saveOrderService;
    private final SaveOrderItemsService saveOrderItemsService;

    public CreateOrderUseCase(
            VerifyStockService verifyStockService,
            PaymentService paymentService,
            ReserveStockService reserveStockService,
            SaveOrderService saveOrderService,
            SaveOrderItemsService saveOrderItemsService) {
        this.verifyStockService = verifyStockService;
        this.paymentService = paymentService;
        this.reserveStockService = reserveStockService;
        this.saveOrderService = saveOrderService;
        this.saveOrderItemsService = saveOrderItemsService;
    }

    public Order execute(Order order) {
        verifyStockService.execute(order);

        boolean paymentSuccess = paymentService.execute(order);
        if (!paymentSuccess) {
            order.setStatus(OrderStatus.FAILED);
            var failedOrderId = saveOrderService.execute(order);
            order.setId(failedOrderId);
            throw new InvalidValueException(ConstantMessage.PAYMENT_FAILED);
        }

        try {
            reserveStockService.execute(order);
        } catch (Exception e) {
            order.setStatus(OrderStatus.FAILED);
            var failedOrderId = saveOrderService.execute(order);
            order.setId(failedOrderId);
            throw new InvalidValueException(ConstantMessage.ERROR_RESERVING_STOCK + e.getMessage());
        }

        order.setStatus(OrderStatus.PAID);
        var orderId = saveOrderService.execute(order);
        order.setId(orderId);

        saveOrderItemsService.execute(order);

        return order;
    }
}