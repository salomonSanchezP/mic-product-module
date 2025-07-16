package com.mic.product.application;

import com.mic.product.domain.ProductMapper;
import com.mic.product.domain.model.ProductDO;
import com.mic.product.infraestructure.ProductEntity;
import com.mic.product.commons.exception.ProductAlreadyExistsException;
import com.mic.product.commons.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;


import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import static org.springframework.util.StringUtils.capitalize;

@Log4j2
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private ProductServiceRepository productServiceRepository;
    private ProductMapper productMapper;


    @Override
    public ProductDO createProduct(ProductDO productDO) {
        ProductEntity productEntityToSave = generateSku(productDO);
        this.existProductBySku(productEntityToSave.getSku());
        ProductEntity productEntity = productServiceRepository.createProduct(productEntityToSave);
        return productMapper.mapToProductDO(productEntity);
    }

    @Override
    public ProductDO updateProduct(String id, ProductDO productDO) {

        ProductDO productDOResult = this.getProductBySku(id);

        ProductEntity productEntity = ProductEntity.builder()
                .name(productDO.getName())
                .productType(productDOResult.getProductType())
                .price(productDO.getPrice())
                .internalCode(productDOResult.getInternalCode())
                .brand(productDOResult.getBrand())
                .sku(productDOResult.getSku())
                .description(productDO.getDescription())
                .color(productDO.getColor())
                .build();

        ProductEntity productEntityUpdated  = productServiceRepository.updateProduct(productEntity);

        return productMapper.mapToProductDO(productEntityUpdated);
    }

    @Override
    public ProductDO patchProduct(String id,ProductDO productDO) {

        this.existProductNotFound(id);
        ProductEntity productEntity = productServiceRepository.getProductBySku(id);

        for (Field field : productEntity.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Id.class)) {
                continue;
            }

            try {
                final Method getter = ProductDO.class.getMethod("get" + capitalize(field.getName()));
                final Object patchValue = getter.invoke(productDO);

                if (patchValue != null) {
                    final Method setter = ProductEntity.class.getMethod("set" + capitalize(field.getName()), field.getType());
                    setter.invoke(productEntity, patchValue);
                }

            } catch (final Exception e) {
                logger.error("Error al procesar campo '{}' en PATCH: {}", field.getName(), e.getMessage());
            }
        }

        ProductEntity productEntityUpdated  = productServiceRepository.updateProduct(productEntity);
        return productMapper.mapToProductDO(productEntityUpdated);
    }

    @Override
    public void deleteProduct(String sku) {
        this.existProductNotFound(sku);
        productServiceRepository.deleteProduct(sku);
    }

    @Override
    public ProductDO getProductBySku(String sku) {
        this.existProductNotFound(sku);
        ProductEntity productEntity = productServiceRepository.getProductBySku(sku);
        return productMapper.mapToProductDO(productEntity);
    }

    @Override
    public List<ProductDO> getAllProducts() {
        List<ProductEntity> productEntities = productServiceRepository.getAllProducts();
        return productMapper.mapToProductDOList(productEntities);
    }

    public void existProductBySku(String sku) {
        if (productServiceRepository.existsBySku(sku)) {
            throw new ProductAlreadyExistsException("Product with SKU " + sku + " already exists");
        }

    }

    public void existProductNotFound(String sku) {
        if (!productServiceRepository.existsBySku(sku)) {
            throw new ResourceNotFoundException("Product with SKU " + sku + " not Found");
        }
    }

    private ProductEntity generateSku(ProductDO productDO) {
        // Formato : MARCA-CODIGOINTERNO-TIPO

        String brand = productDO.getBrand().toUpperCase().replaceAll("\\s", "");
        String internalCode = productDO.getInternalCode().toUpperCase().replaceAll("\\s", "");
        String type = productDO.getProductType().name().toUpperCase().replaceAll("\\s", "");

        String skuBuilder = brand + "-" +
                internalCode + "-" +
                type;

       return ProductEntity.builder()
                .color(productDO.getColor())
                .price(productDO.getPrice())
                .name(productDO.getName())
                .description(productDO.getDescription())
                .internalCode(productDO.getInternalCode())
                .brand(productDO.getBrand())
                .productType(productDO.getProductType())
                .sku(skuBuilder)
                .build();
    }

}
