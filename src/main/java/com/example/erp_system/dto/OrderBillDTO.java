package com.example.erp_system.dto;

import com.example.erp_system.entity.CustomerOrderEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Setter
public class OrderBillDTO {

    private UUID uuid;
    private CustomerOrderEntity order;
    private BigDecimal totalPrice;

    public OrderBillDTO() {

        this.uuid = UUID.randomUUID();
    }
}
