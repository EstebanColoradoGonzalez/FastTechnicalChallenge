package co.com.ceiba.reto.module.infrastructure.adapter.entity;

import co.com.ceiba.reto.common.domain.constant.ConstantNumber;
import co.com.ceiba.reto.common.domain.constant.ConstantText;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = ConstantText.ORDER_ITEMS_TABLE)
public class OrderItemEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = ConstantText.ORDER_ITEMS_SEQ
    )
    @SequenceGenerator(
            name = ConstantText.ORDER_ITEMS_SEQ,
            sequenceName = ConstantText.ORDER_ITEMS_SEQ,
            allocationSize = 1
    )
    @Column(name = ConstantText.ID)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(
            name = ConstantText.ORDER_ID,
            nullable = false
    )
    private OrderEntity order;

    @Column(name = ConstantText.PRODUCT_ID,
            nullable = false
    )
    private Integer productId;

    @Column(name = ConstantText.QUANTITY,
            nullable = false
    )
    private Integer quantity;

    @Column(name = ConstantText.UNIT_PRICE,
            nullable = false,
            precision = ConstantNumber.DECIMAL_PRECISION,
            scale = ConstantNumber.DECIMAL_SCALE
    )
    private BigDecimal unitPrice;
}