package com.example.erp_system.controller;


import com.example.erp_system.dto.CustomerDTO;
import com.example.erp_system.entity.CustomerEntity;
import com.example.erp_system.service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerEntity>> getAll() {
        return new ResponseEntity<>(customerService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<CustomerEntity>> getAllByName(@PathVariable String name) {
        return new ResponseEntity<>(customerService.getAllByNameIContains(name), HttpStatus.OK);
    }

    @PostMapping("create-customer")
    public ResponseEntity<Boolean> createCustomer(@RequestBody CustomerDTO customer) {
        return  new ResponseEntity<>(customerService.createCustomer(customer.getName(), customer.getSurname(),
                customer.getEmail(), customer.getTc()), HttpStatus.CREATED);
    }

    @Modifying
    @Transactional
    @PutMapping("update-customer/{uuid}")
    public ResponseEntity<Boolean> updateCustomerByUuid(@PathVariable UUID uuid, @RequestBody CustomerDTO customer) {

        CustomerEntity newCustomer = new CustomerEntity();
        newCustomer.setName(customer.getName());
        newCustomer.setSurname(customer.getSurname());
        newCustomer.setEmail(customer.getEmail());
        newCustomer.setTc(customer.getTc());
        return new ResponseEntity<>(customerService.updateCustomer(uuid, newCustomer), HttpStatus.OK);

    }

    @Modifying
    @Transactional
    @DeleteMapping("delete-customer/{uuid}")
    public ResponseEntity<Boolean> deleteCustomerByUUid(@PathVariable UUID uuid) {
        return new ResponseEntity<>(customerService.deleteCustomer(uuid), HttpStatus.OK);
    }


}
