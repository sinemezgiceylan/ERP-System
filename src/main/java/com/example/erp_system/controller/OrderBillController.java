package com.example.erp_system.controller;


import com.example.erp_system.dto.OrderBillDTO;
import com.example.erp_system.entity.OrderBillEntity;
import com.example.erp_system.service.OrderBillService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("orderBill")
public class OrderBillController {

    @Autowired
    OrderBillService orderBillService;

    @GetMapping
    public ResponseEntity<List<OrderBillEntity>> getAll() {
        return new ResponseEntity<>(orderBillService.getAll(), HttpStatus.OK);
    }

    @GetMapping("get/{uuid}")
    public ResponseEntity<OrderBillEntity> getByUuid(@PathVariable UUID uuid) {
        return new ResponseEntity<>(orderBillService.getByUuid(uuid), HttpStatus.OK);
    }

    @PostMapping("create-order-bill")
    public ResponseEntity<Boolean> createOrderBill(@RequestBody OrderBillDTO orderBill) {
        return new ResponseEntity<>(orderBillService.createOrderBill(orderBill.getOrder(),
                orderBill.getTotalPrice()), HttpStatus.CREATED);
    }

    @Modifying
    @Transactional
    @PutMapping("update-customer-bill/{uuid}")
    public ResponseEntity<Boolean> updateOrderBillByUuid(@PathVariable UUID uuid, @RequestBody OrderBillDTO orderBill) {
        OrderBillEntity newOrderBill = new OrderBillEntity();
        newOrderBill.setOrder(orderBill.getOrder());
        newOrderBill.setTotalPrice(orderBill.getTotalPrice());
        return new ResponseEntity<>(orderBillService.updateOrderBill(uuid, newOrderBill), HttpStatus.OK);
    }

    @Modifying
    @Transactional
    @DeleteMapping("delete-customer-bill/{uuid}")
    public ResponseEntity<Boolean> deleteOrderBillByUuid(@PathVariable UUID uuid) {
        return new ResponseEntity<>(orderBillService.deleteOrderBill(uuid), HttpStatus.OK);
    }
}
