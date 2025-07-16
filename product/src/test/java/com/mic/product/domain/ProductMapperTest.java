package com.mic.product.domain;

import com.mic.product.domain.model.ProductDO;
import com.mic.product.factory.CheckNullValue;
import com.mic.product.factory.ProductFactory;
import com.mic.product.infraestructure.ProductEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductMapperTest {

    private final ProductMapper productMapper = new ProductMapperImpl();

    @Test
    void givenMapToProductDO_whenAllIsOk_thenReturnProduct() {
        final ProductEntity productEntity = ProductFactory.createProductEntity();
        final ProductDO productDO = ProductFactory.createProductDO();

        assertThat(this.productMapper.mapToProductDO(productEntity))
                .isNotNull()
                .isEqualTo(productDO);
    }

    @Test
    void givenMapToProductDO_whenProductIsNull_thenReturnProduct() {
        final ProductEntity productEntity = CheckNullValue.sneakyNullReference();

        assertThat(this.productMapper.mapToProductDO(productEntity))
                .isNull();
    }

    @Test
    void givenMapToProductDO_whenAllDataIsNull_thenReturnProduct() {
        final ProductEntity productEntity = new ProductEntity();
        final ProductDO productDO = new ProductDO();

        assertThat(this.productMapper.mapToProductDO(productEntity))
                .isNotNull()
                .isEqualTo(productDO);
    }

    @Test
    void givenMapToProductDOList_whenAllIsOk_thenResponseProducts() {
        final ProductEntity productEntity = ProductFactory.createProductEntity();
        final ProductDO productDO = ProductFactory.createProductDO();
        final List<ProductEntity> productEntityList = List.of(productEntity);

        assertThat(this.productMapper.mapToProductDOList(productEntityList))
                .isNotNull()
                .hasSize(1)
                .containsExactly(productDO);
    }

    @Test
    void givenMapToProductDOList_whenListIsEmpty_thenResponseEmpty() {
        final List<ProductEntity> productEntityList = List.of();

        assertThat(this.productMapper.mapToProductDOList(productEntityList))
                .isEmpty();
    }
}