package com.example.erp_system.repository;

import com.example.erp_system.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    List<CustomerEntity> findAllByNameContainsIgnoreCase(String name);

    CustomerEntity findByUuid(UUID uuid);

    void deleteByUuid(UUID uuid);


}
