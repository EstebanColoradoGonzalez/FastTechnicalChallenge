package co.com.ceiba.reto.module.infrastructure.controller;

import co.com.ceiba.reto.common.domain.constant.ConstantText;
import co.com.ceiba.reto.module.application.command.CreateOrderCommand;
import co.com.ceiba.reto.module.application.command.handler.CreateOrderHandler;
import co.com.ceiba.reto.module.application.dto.OrderDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(ConstantText.ORDERS_API)
public class OrderCommandController {
    private final CreateOrderHandler createOrderHandler;

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody CreateOrderCommand createOrderCommand) {
        return new ResponseEntity<>(createOrderHandler.execute(createOrderCommand), HttpStatus.CREATED);
    }
}