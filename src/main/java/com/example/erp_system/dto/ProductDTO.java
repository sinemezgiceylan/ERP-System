package com.example.erp_system.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Setter
public class ProductDTO {

    private UUID uuid;
    private String name;
    private BigDecimal price;
    private KdvDTO kdv;
    private Boolean isKdvApplied;
    private BigDecimal nonKdvApplied = BigDecimal.ZERO;
    private Integer stockCount;
    private CustomerOrderDTO order; // !!!!!!!
    private int orderCount;

    public ProductDTO() {

        this.uuid = UUID.randomUUID();
    }
}
