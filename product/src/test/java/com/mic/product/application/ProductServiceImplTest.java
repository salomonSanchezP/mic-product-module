package com.mic.product.application;
import com.mic.product.commons.exception.ProductAlreadyExistsException;
import com.mic.product.commons.exception.ResourceNotFoundException;
import com.mic.product.domain.ProductMapper;
import com.mic.product.domain.model.ProductDO;
import com.mic.product.factory.ProductFactory;
import com.mic.product.infraestructure.ProductEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;



import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductServiceRepository productServiceRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void givenCreateProduct_whenNotExistProduct_theReturnProduct() {
        final String sku = ProductFactory.sku;
        ProductEntity entity = ProductFactory.createProductEntity();
        ProductDO productDO = ProductFactory.createProductDO();

        given(this.productServiceRepository.existsBySku(sku)).willReturn(false);
        given(this.productServiceRepository.createProduct(entity)).willReturn(entity);
        given(this.productMapper.mapToProductDO(entity)).willReturn(productDO);

        assertThat(this.productService.createProduct(productDO))
                .isNotNull()
                .isEqualTo(productDO);
    }

    @Test
    void givenCreateProduct_whenExistProduct_theReturnThrowException() {
        final String sku = ProductFactory.sku;

        ProductDO productDO = ProductFactory.createProductDO();

        given(this.productServiceRepository.existsBySku(sku)).willReturn(true);

        assertThatThrownBy(() -> this.productService.createProduct(productDO))
                .isInstanceOf(ProductAlreadyExistsException.class)
                .message()
                .contains("Product with SKU " + sku + " already exists");
    }

    @Test
    void givenUpdateProduct_whenExistProduct_thenReturnProduct() {
        final String sku = ProductFactory.sku;
        ProductEntity productEntity = ProductFactory.createProductEntity();
        ProductDO productDO = ProductFactory.createProductDO();

        given(this.productServiceRepository.existsBySku(sku)).willReturn(true);
        given(this.productServiceRepository.getProductBySku(sku)).willReturn(productEntity);
        given(this.productMapper.mapToProductDO(productEntity)).willReturn(productDO);
        given(this.productServiceRepository.updateProduct(productEntity)).willReturn(productEntity);
        given(this.productMapper.mapToProductDO(productEntity)).willReturn(productDO);

        assertThat(this.productService.updateProduct(sku, productDO))
                .isNotNull()
                .isEqualTo(productDO);
    }

    @Test
    void givenUpdateProduct_whenNotExistProduct_thenReturnProduct() {
        final String sku = ProductFactory.sku;
        ProductDO productDO = ProductFactory.createProductDO();

        given(this.productServiceRepository.existsBySku(sku)).willReturn(false);

        assertThatThrownBy(() -> this.productService.updateProduct(sku, productDO))
                .isInstanceOf(ResourceNotFoundException.class)
                .message()
                .contains("Product with SKU " + sku + " not Found");

        verify(this.productServiceRepository, times(1)).existsBySku(sku);
    }

    @Test
    void givenPatchProduct_whenExistProduct_thenReturnProduct() {
        final String sku = ProductFactory.sku;
        ProductEntity productEntity = ProductFactory.createProductEntity();
        ProductDO productDO = ProductDO.builder()
                .name("Test Product patch")
                .build();

        given(this.productServiceRepository.existsBySku(sku)).willReturn(true);
        given(this.productServiceRepository.getProductBySku(sku)).willReturn(productEntity);
        given(this.productServiceRepository.updateProduct(productEntity)).willReturn(productEntity);
        given(this.productMapper.mapToProductDO(productEntity)).willReturn(productDO);

        assertThat(this.productService.patchProduct(sku, productDO))
                .isNotNull()
                .isEqualTo(productDO);
    }

    @Test
    void givenDeleteProduct_whenExistProduct_thenReturnOk() {
        final String sku = ProductFactory.sku;

        given(this.productServiceRepository.existsBySku(sku)).willReturn(true);
        this.productService.deleteProduct(sku);

        verify(this.productServiceRepository, times(1)).deleteProduct(sku);
    }

    @Test
    void givenDeleteProduct_whenNotExistProduct_thenThrowException() {
        final String sku = ProductFactory.sku;

        given(this.productServiceRepository.existsBySku(sku)).willReturn(false);

        assertThatThrownBy(() -> this.productService.deleteProduct(sku))
                .isInstanceOf(ResourceNotFoundException.class)
                .message()
                .contains("Product with SKU " + sku + " not Found");

        verify(this.productServiceRepository, times(1)).existsBySku(sku);
    }

    @Test
    void givenGetProductBySku_whenExistProduct_thenReturnProduct() {
        final String sku = ProductFactory.sku;
        ProductEntity productEntity = ProductFactory.createProductEntity();
        ProductDO productDO = ProductFactory.createProductDO();

        given(this.productServiceRepository.existsBySku(sku)).willReturn(true);
        given(this.productServiceRepository.getProductBySku(sku)).willReturn(productEntity);
        given(this.productMapper.mapToProductDO(productEntity)).willReturn(productDO);

        assertThat(this.productService.getProductBySku(sku))
                .isNotNull()
                .isEqualTo(productDO);

    }

    @Test
    void givenGetProductBySku_whenNotExistProduct_thenReturnProduct() {
        final String sku = ProductFactory.sku;

        given(this.productServiceRepository.existsBySku(sku)).willReturn(false);

        assertThatThrownBy(() -> this.productService.getProductBySku(sku))
                .isInstanceOf(ResourceNotFoundException.class)
                .message()
                .contains("Product with SKU " + sku + " not Found");

        verify(this.productServiceRepository, times(1)).existsBySku(sku);

    }

    @Test
    void givenGetAllProducts_whenAllDataExist_thenReturnOK() {

        List<ProductEntity> productEntities = ProductFactory.createProductEntityList();
        List<ProductDO> productDOS = ProductFactory.createProductDOList();

        given(this.productServiceRepository.getAllProducts()).willReturn(productEntities);
        given(this.productMapper.mapToProductDOList(productEntities)).willReturn(productDOS);

        assertThat(this.productService.getAllProducts())
                .isNotNull()
                .isEqualTo(productDOS);
    }

}