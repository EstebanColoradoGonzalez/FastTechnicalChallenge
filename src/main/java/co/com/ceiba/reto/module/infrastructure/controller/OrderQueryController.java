package co.com.ceiba.reto.module.infrastructure.controller;

import co.com.ceiba.reto.common.domain.constant.ConstantText;
import co.com.ceiba.reto.module.application.dto.OrderDTO;
import co.com.ceiba.reto.module.application.query.GetOrderHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(ConstantText.ORDERS_API)
public class OrderQueryController {
    private final GetOrderHandler getOrderHandler;

    @GetMapping(ConstantText.ORDER_ID_PARAMETER)
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long orderId) {
        return new ResponseEntity<>(getOrderHandler.execute(orderId), HttpStatus.OK);
    }
}