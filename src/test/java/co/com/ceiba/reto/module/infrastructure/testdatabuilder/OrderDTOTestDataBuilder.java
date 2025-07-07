package co.com.ceiba.reto.module.infrastructure.testdatabuilder;

import co.com.ceiba.reto.module.application.dto.OrderDTO;
import co.com.ceiba.reto.module.application.dto.OrderItemDTO;

import java.math.BigDecimal;
import java.util.List;

public class OrderDTOTestDataBuilder {

    private Long orderId;
    private List<OrderItemDTO> items;
    private BigDecimal totalAmount;
    private String status;

    public OrderDTOTestDataBuilder() {
        this.orderId = 1L;
        this.items = List.of(new OrderItemDTOTestDataBuilder().build());
        this.totalAmount = BigDecimal.valueOf(450.00);
        this.status = "PAID";
    }

    public OrderDTOTestDataBuilder withOrderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public OrderDTOTestDataBuilder withItems(List<OrderItemDTO> items) {
        this.items = items;
        return this;
    }

    public OrderDTOTestDataBuilder withTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public OrderDTOTestDataBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public OrderDTO build() {
        return new OrderDTO(orderId, items, totalAmount, status);
    }
}