package com.example.erp_system.repository;

import com.example.erp_system.entity.KdvEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KdvRepository extends JpaRepository<KdvEntity, Long> {
}
