package com.mic.product.infraestructure;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<ProductEntity, String> {

    ProductEntity findBySku(String sku);
    void deleteBySku(String sku);
    boolean existsBySku(String sku);
}
