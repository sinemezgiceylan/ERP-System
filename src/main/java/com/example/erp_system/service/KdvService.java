package com.example.erp_system.service;

import com.example.erp_system.entity.KdvEntity;
import com.example.erp_system.repository.KdvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class KdvService {
    @Autowired
    KdvRepository kdvRepository;


    // Kdv yaratıldı.
    public boolean createKdv(String name, BigDecimal percent) {
        if (name == null || percent == null) {
            return false;
        } else {
            KdvEntity kdvEntity = new KdvEntity();
            kdvEntity.setName(name);
            kdvEntity.setPercent(percent);

            kdvRepository.save(kdvEntity);
            return true;
        }
    }

    public List<KdvEntity> getAll() {
        return kdvRepository.findAll();
    }

    public List<KdvEntity> getAllByNameIContains(String name) {
        return kdvRepository.findAllByNameContainsIgnoreCase(name);
    }

    // UUID'ye göre kdv güncellendi.
    public boolean updateKdv(UUID uuid, KdvEntity kdvEntity) {
        if (uuid == null || kdvEntity == null) {
            return false;
        } else {
            KdvEntity existKdv = kdvRepository.findByUuid(uuid);
            if (existKdv == null) {
                return false;
            } else {
                existKdv.setName(kdvEntity.getName());
                existKdv.setPercent(kdvEntity.getPercent());

                kdvRepository.save(existKdv);
                return true;
            }
        }
    }

    public boolean deleteKdv(UUID uuid) {
        if (uuid == null) {
            return false;
        } else {
            kdvRepository.deleteByUuid(uuid);
            return true;
        }
    }


}
