package com.example.erp_system.service;

import com.example.erp_system.repository.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerOrderService {
    @Autowired
    CustomerOrderRepository customerOrderRepository;
}
