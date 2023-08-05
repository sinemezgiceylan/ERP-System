package com.example.erp_system.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Table
@Data
@Entity
@AttributeOverride(name = "uuid", column = @Column(name = "order_bill_uuid"))
public class OrderBillEntity extends BaseEntity{

    @OneToOne
    @JoinColumn(name = "order_id", unique = false)
    private CustomerOrderEntity order;

    @Column
    private BigDecimal totalPrice;
}
