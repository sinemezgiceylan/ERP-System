package com.example.erp_system.service;

import com.example.erp_system.entity.CustomerEntity;
import com.example.erp_system.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    // Yeni müsteri oluşturuldu.

    public boolean createCustomer(String name, String surname, String email, String tc) {
        if (name == null || surname == null || email == null || tc == null) {
            return false;
        } else {
            CustomerEntity customerEntity = new CustomerEntity();
            customerEntity.setName(name);
            customerEntity.setSurname(surname);
            customerEntity.setEmail(email);
            customerEntity.setTc(tc);

            customerRepository.save(customerEntity);
            return true;
        }
    }

    public List<CustomerEntity> getAll() {
        return customerRepository.findAll();
    }

    public List<CustomerEntity> getAllByNameIContains(String name) {
        return customerRepository.findAllByNameContainsIgnoreCase(name);
    }


    // UUID'ye göre müşteri bilgileri güncellendi.

    public boolean updateCustomer(UUID uuid, CustomerEntity customerEntity) {
        if (uuid == null || customerEntity == null) {
            return false;
        } else {
            CustomerEntity existCustomer = customerRepository.findByUuid(uuid);
            if (existCustomer == null) {
                return false;
            }
            existCustomer.setName(customerEntity.getName());
            existCustomer.setSurname(customerEntity.getSurname());
            existCustomer.setEmail(customerEntity.getEmail());
            existCustomer.setTc(customerEntity.getTc());

            customerRepository.save(existCustomer);

            return true;
        }
    }

    // UUID'ye göre müşteri silindi.

    public boolean deleteCustomer(UUID uuid) {
        if (uuid == null) {
            return false;
        } else {
            customerRepository.deleteByUuid(uuid);
            return true;
        }
    }
}
