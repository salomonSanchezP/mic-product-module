package com.mic.product.application;

import com.mic.product.domain.model.ProductDO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    ProductDO createProduct(ProductDO productDO);
    ProductDO updateProduct(String id, ProductDO productDO);
    ProductDO patchProduct(String id, ProductDO productDO);
    void deleteProduct(String sku);
    ProductDO getProductBySku(String sku);
    List<ProductDO> getAllProducts();
}
