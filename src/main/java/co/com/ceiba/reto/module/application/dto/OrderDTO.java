package co.com.ceiba.reto.module.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Long orderId;
    private List<OrderItemDTO> items;
    private BigDecimal totalAmount;
    private String status;
}
