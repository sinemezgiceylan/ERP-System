package com.example.erp_system.service;

import com.example.erp_system.entity.KdvEntity;
import com.example.erp_system.entity.ProductEntity;
import com.example.erp_system.repository.KdvRepository;
import com.example.erp_system.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    KdvRepository kdvRepository;

    // Yeni ürün oluşturuldu.

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

    // UUID'ye göre ürün güncellendi.

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

    // UUID'ye göre ürün silindi.

    public boolean deleteProduct(UUID uuid) {
        if (uuid == null)
            return false;
        else {
            productRepository.deleteByUuid(uuid);
            return true;
        }
    }

    // Kdv hesabı yapıldı.

    public void kdvTruePrice(ProductEntity product) {
        BigDecimal kdv = product.getKdv().getPercent();
        BigDecimal price = product.getPrice();
        BigDecimal totalPrice;
        BigDecimal kdvPrice;
        if (!product.getIsKdvApplied()) {
            product.setNonKdvApplied(price);
            kdvPrice = (price.multiply(kdv)).divide(new BigDecimal(100), MathContext.DECIMAL32);
            totalPrice = price.add(kdvPrice);
            product.setPrice(totalPrice);
        } else {
            totalPrice = price;
            BigDecimal nonKdvPrice = (totalPrice.multiply(new BigDecimal(100))).divide((new BigDecimal(100)).add(kdv), MathContext.DECIMAL32);
            product.setNonKdvApplied(nonKdvPrice);
        }
    }

}
