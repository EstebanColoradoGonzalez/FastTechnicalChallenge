package co.com.ceiba.reto.module.infrastructure.adapter.entity;

import co.com.ceiba.reto.common.domain.constant.ConstantNumber;
import co.com.ceiba.reto.common.domain.constant.ConstantText;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = ConstantText.ORDERS_TABLE)
public class OrderEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = ConstantText.ORDERS_SEQ
    )
    @SequenceGenerator(
            name = ConstantText.ORDERS_SEQ,
            sequenceName = ConstantText.ORDERS_SEQ,
            allocationSize = 1
    )
    @Column(name = ConstantText.ORDER_ID)
    private Long id;

    @Column(name = ConstantText.CUSTOMER_ID,
            nullable = false
    )
    private Integer customerId;

    @Column(name = ConstantText.TOTAL_AMOUNT,
            nullable = false,
            precision = ConstantNumber.DECIMAL_PRECISION,
            scale = ConstantNumber.DECIMAL_SCALE
    )
    private BigDecimal totalAmount;

    @Column(name = ConstantText.STATUS,
            nullable = false,
            length = 20
    )
    private String status;

    @Column(name = ConstantText.PAYMENT_METHOD,
            nullable = false,
            length = 20
    )
    private String paymentMethod;

    @OneToMany(mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderItemEntity> items;
}