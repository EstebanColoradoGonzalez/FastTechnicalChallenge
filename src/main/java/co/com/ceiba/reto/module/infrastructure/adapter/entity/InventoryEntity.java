package co.com.ceiba.reto.module.infrastructure.adapter.entity;

import co.com.ceiba.reto.common.domain.constant.ConstantNumber;
import co.com.ceiba.reto.common.domain.constant.ConstantText;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = ConstantText.INVENTORY_TABLE)
public class InventoryEntity {
    @Id
    @Column(name = ConstantText.PRODUCT_ID)
    private Integer productId;

    @Column(name = ConstantText.STOCK, nullable = false)
    private Integer stock;

    @Column(name = ConstantText.UNIT_PRICE, nullable = false, precision = ConstantNumber.DECIMAL_PRECISION, scale = ConstantNumber.DECIMAL_SCALE)
    private BigDecimal unitPrice;
}