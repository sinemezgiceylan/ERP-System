package com.example.erp_system.entity;

import com.example.erp_system.dto.CustomerOrderDTO;
import com.example.erp_system.dto.KdvDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table
@Data
@AttributeOverride(name = "uuid", column = @Column(name = "product_uuid"))
public class ProductEntity extends BaseEntity{

    @Column
    private String name;

    @Column
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "kdv_id", unique = false)
    private KdvEntity kdv;

    @Column
    private Boolean isKdvApplied;

    @Column
    private BigDecimal nonKdvApplied = BigDecimal.ZERO;

    @Column
    private Integer stockCount;

    @Column
    private int orderCount;

}
