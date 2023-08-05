package com.example.erp_system.entity;

import com.example.erp_system.util.StatusEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
@AttributeOverride(name = "uuid", column = @Column(name = "customer_order_uuid"))
public class CustomerOrderEntity extends BaseEntity{

    @OneToOne // One to one
    @JoinColumn(name = "customer_id", unique = false)
    private CustomerEntity customer;

    @ManyToMany
    @JoinColumn(name = "product_id", unique = false)
    private List<ProductEntity> productList = new ArrayList<>();

    @Column
    private StatusEnum status = StatusEnum.WAITING;

    @Column
    private BigDecimal totalPrice;
}
