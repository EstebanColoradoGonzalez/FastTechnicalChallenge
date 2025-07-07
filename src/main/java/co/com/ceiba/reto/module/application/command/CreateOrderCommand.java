package co.com.ceiba.reto.module.application.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderCommand {
    private Integer customerId;
    private List<CreateOrderItemCommand> items;
    private String paymentMethod;
}