package com.example.erp_system.controller;

import com.example.erp_system.dto.KdvDTO;
import com.example.erp_system.entity.KdvEntity;
import com.example.erp_system.service.KdvService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("kdv")
public class KdvController {

    @Autowired
    KdvService kdvService;

    @GetMapping
    public ResponseEntity<List<KdvEntity>> getAll() {
        return new ResponseEntity<>(kdvService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<KdvEntity>> getAllByName(@PathVariable String name) {
        return new ResponseEntity<>(kdvService.getAllByNameIContains(name), HttpStatus.OK);
    }

    @PostMapping("create-kdv")
    public ResponseEntity<Boolean> crateKdv(@RequestBody KdvDTO kdv) {
        return new ResponseEntity<>(kdvService.createKdv(kdv.getName(), kdv.getPercent()), HttpStatus.CREATED);
    }

    @Modifying
    @Transactional
    @PutMapping("update-kdv/{uuid}")
    public ResponseEntity<Boolean> updateKdvByUuid(@PathVariable UUID uuid, @RequestBody KdvDTO kdv) {
        KdvEntity newKdv = new KdvEntity();

        newKdv.setName(kdv.getName());
        newKdv.setPercent(kdv.getPercent());

        return new ResponseEntity<>(kdvService.updateKdv(uuid, newKdv), HttpStatus.OK);
    }

    @Modifying
    @Transactional
    @DeleteMapping("delete-kdv/{uuid}")
    public ResponseEntity<Boolean> deleteKdvByUuid(@PathVariable UUID uuid) {
        return new ResponseEntity<>(kdvService.deleteKdv(uuid), HttpStatus.OK);
    }

}
