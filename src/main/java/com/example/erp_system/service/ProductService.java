package com.example.erp_system.service;

import com.example.erp_system.entity.KdvEntity;
import com.example.erp_system.entity.ProductEntity;
import com.example.erp_system.repository.KdvRepository;
import com.example.erp_system.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    KdvRepository kdvRepository;

    public boolean createProduct(String name, BigDecimal price, KdvEntity kdv, boolean isKdvApplied, Integer stockCount) {
        if (name == null || price == null || kdv == null || stockCount == null) {
            return false;
        } else {
            ProductEntity productEntity = new ProductEntity();

            productEntity.setName(name);
            productEntity.setPrice(price);
            productEntity.setKdv(kdvRepository.findByUuid(kdv.getUuid()));
            productEntity.setIsKdvApplied(isKdvApplied);
            productEntity.setStockCount(stockCount);
            productEntity.setOrderCount(1);
            kdvTruePrice(productEntity);
            productRepository.save(productEntity);
            return true;

        }
    }

    public List<ProductEntity> getAll() {
        return productRepository.findAll();
    }

    public ProductEntity getByUuid(UUID uuid) {
        return productRepository.findByUuid(uuid);
    }

    public List<ProductEntity> getAllByNameContainsIgnoreCase(String name) {
        return productRepository.findAllByNameContainsIgnoreCase(name);
    }

    public boolean updateProduct(UUID uuid, ProductEntity productEntity) {
        if (uuid == null || productEntity == null) {
            return false;
        } else {
            ProductEntity existProduct = productRepository.findByUuid(uuid);
            if (existProduct == null) {
                return false;
            }

            existProduct.setName(productEntity.getName());
            existProduct.setPrice(productEntity.getPrice());
            existProduct.setKdv(productEntity.getKdv());
            existProduct.setIsKdvApplied(productEntity.getIsKdvApplied());
            existProduct.setNonKdvApplied(productEntity.getNonKdvApplied());
            existProduct.setStockCount(productEntity.getStockCount());
            kdvTruePrice(existProduct);
            productRepository.save(existProduct);
            return true;

        }
    }

    public boolean deleteProduct(UUID uuid) {
        if (uuid == null)
            return false;
        else {
            productRepository.deleteByUuid(uuid);
            return true;
        }
    }

    public void kdvTruePrice(ProductEntity product) {
        BigDecimal kdv = product.getKdv().getPercent();
        BigDecimal price = product.getPrice();
        if (!product.getIsKdvApplied()) {
            product.setNonKdvApplied(price);
            BigDecimal kdvPrice = (price.multiply(kdv)).divide(new BigDecimal(100));
            BigDecimal totalPrice = price.add(kdvPrice);
            product.setPrice(totalPrice);
        } else {
            BigDecimal kdvPrice = (price.multiply(kdv)).divide(BigDecimal.valueOf(100));
            BigDecimal nonKdvPrice = price.subtract(kdvPrice);
            product.setNonKdvApplied(nonKdvPrice);
        }
    }

}
