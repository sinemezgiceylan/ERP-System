package com.example.erp_system.service;

import com.example.erp_system.repository.OrderBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderBillService {
    @Autowired
    OrderBillRepository orderBillRepository;
}
