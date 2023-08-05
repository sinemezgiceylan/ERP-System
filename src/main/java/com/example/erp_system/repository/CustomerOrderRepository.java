package com.example.erp_system.repository;

import com.example.erp_system.entity.CustomerOrderEntity;
import com.example.erp_system.util.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrderEntity, Long> {

    List<CustomerOrderEntity> getCustomerOrderEntitiesByStatus(StatusEnum statusEnum);

    CustomerOrderEntity findByUuid(UUID uuid);

    void deleteByUuid(UUID uuid);
}