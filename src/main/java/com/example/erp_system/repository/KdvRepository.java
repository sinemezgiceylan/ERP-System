package com.example.erp_system.repository;

import com.example.erp_system.entity.CustomerEntity;
import com.example.erp_system.entity.KdvEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface KdvRepository extends JpaRepository<KdvEntity, Long> {

    List<KdvEntity> findAllByNameContainsIgnoreCase(String name);

    KdvEntity findByUuid(UUID uuid);

    void deleteByUuid(UUID uuid);
}
