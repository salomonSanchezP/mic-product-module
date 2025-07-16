package com.mic.product.application;

import com.mic.product.infraestructure.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductServiceRepository {
    ProductEntity createProduct(ProductEntity productEntity);
    ProductEntity updateProduct(ProductEntity productEntity);
    void deleteProduct(String sku);
    ProductEntity getProductBySku(String sku);
    List<ProductEntity> getAllProducts();
    Boolean existsBySku(String sku);
}
