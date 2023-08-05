package com.example.erp_system.repository;

import com.example.erp_system.entity.OrderBillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface OrderBillRepository extends JpaRepository<OrderBillEntity, Long> {

    OrderBillEntity findByUuid(UUID uuid);

    void deleteByUuid(UUID uuid);
}
