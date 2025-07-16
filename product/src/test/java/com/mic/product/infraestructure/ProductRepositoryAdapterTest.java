package com.mic.product.infraestructure;

import com.mic.product.factory.CheckNullValue;
import com.mic.product.factory.ProductFactory;
import com.mongodb.MongoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.engine.support.hierarchical.ThrowableCollector;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryAdapterTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductRepositoryAdapter productRepositoryAdapter;

    @Test
    void givenCreateProduct_whenExistProduct_thenReturnProduct() {

        final ProductEntity productEntity = ProductFactory.createProductEntity();

        given(this.productRepository.save(productEntity)).willReturn(productEntity);

        assertThat(this.productRepositoryAdapter.createProduct(productEntity))
                .isNotNull()
                .isEqualTo(productEntity);
    }

    @Test
    void givenCreateProduct_whenErrorOccur_thenReturnThrowException() {
        final ProductEntity productEntity = ProductFactory.createProductEntity();
        final MongoException exception = new MongoException("e");

        given(this.productRepository.save(productEntity)).willThrow(exception);

        assertThatThrownBy(() -> this.productRepositoryAdapter.createProduct(productEntity) )
                .isInstanceOf(MongoException.class)
                .isEqualTo(exception);
    }

    @Test
    void givenCreateProduct_whenErrorOccurNull_thenReturnThrowException() {
        final ProductEntity productEntity = CheckNullValue.sneakyNullReference();
        final NullPointerException exception = assertThrows(NullPointerException.class,
                () -> this.productRepositoryAdapter.createProduct(productEntity));

        assertThat(exception.getMessage()).contains("is null");
    }

    @Test
    void givenIUpdateProduct_whenExistProduct_thenReturnProduct() {
        final ProductEntity productEntity = ProductFactory.createProductEntity();

        given(this.productRepository.save(productEntity)).willReturn(productEntity);

        assertThat(this.productRepositoryAdapter.updateProduct(productEntity))
                .isNotNull()
                .isEqualTo(productEntity);
    }

    @Test
    void givenIUpdateProduct_whenErrorOccur_thenReturnThrowException() {
        final ProductEntity productEntity = ProductFactory.createProductEntity();
        final MongoException exception = new MongoException("e");

        given(this.productRepository.save(productEntity)).willThrow(exception);

        assertThatThrownBy(() -> this.productRepositoryAdapter.updateProduct(productEntity))
                .isInstanceOf(MongoException.class)
                .isEqualTo(exception);
    }

    @Test
    void givenIUpdateProduct_whenErrorOccurNullPoint_thenReturnThrowException() {
        final ProductEntity productEntity = CheckNullValue.sneakyNullReference();

        final NullPointerException exception = assertThrows(NullPointerException.class,
                () -> this.productRepositoryAdapter.updateProduct(productEntity));

        assertThat(exception.getMessage()).contains("is null");
    }

    @Test
    void givenDeleteProduct_whenExistProduct_thenReturnProduct() {
        final String sku = ProductFactory.sku;

        doNothing().when(this.productRepository).deleteBySku(sku);

        this.productRepositoryAdapter.deleteProduct(sku);

        then(this.productRepository)
                .should(times(1))
                .deleteBySku(sku);

    }

    @Test
    void givenDeleteProduct_whenErrorOccur_thenReturnThrowException() {
        final String sku = ProductFactory.sku;
        final MongoException exception = new MongoException("e");

        doThrow(exception).when(productRepository).deleteBySku(sku);

        assertThatThrownBy(() -> this.productRepositoryAdapter.deleteProduct(sku))
                .isInstanceOf(MongoException.class)
                .isEqualTo(exception);
    }

    @Test
    void givenGetProductBySku_whenExistProduct_thenReturnProduct() {
        final String sku = ProductFactory.sku;
        final ProductEntity productEntity = ProductFactory.createProductEntity();

        given(this.productRepository.findBySku(sku)).willReturn(productEntity);

        assertThat(this.productRepositoryAdapter.getProductBySku(sku))
                .isNotNull()
                .isEqualTo(productEntity);
    }

    @Test
    void givenGetProductBySku_whenErrorOccur_thenReturnThrowException() {
        final String sku = ProductFactory.sku;
        final MongoException exception = new MongoException("e");

        given(this.productRepository.findBySku(sku)).willThrow(exception);

        assertThatThrownBy(() -> this.productRepositoryAdapter.getProductBySku(sku))
                .isInstanceOf(MongoException.class)
                .isEqualTo(exception);
    }

    @Test
    void givenGetAllProducts_whenAllIsOk_thenReturnProducts() {
        final List<ProductEntity> productEntities = ProductFactory.createProductEntityList();

        given(this.productRepository.findAll()).willReturn(productEntities);

        assertThat(this.productRepositoryAdapter.getAllProducts())
                .isNotNull()
                .isEqualTo(productEntities);
    }

    @Test
    void givenGetAllProducts_whenErrorOccur_thenReturnThrowException() {
        final MongoException exception = new MongoException("e");

        given(this.productRepository.findAll()).willThrow(exception);

        assertThatThrownBy(() -> this.productRepositoryAdapter.getAllProducts())
                .isInstanceOf(MongoException.class)
                .isEqualTo(exception);
    }

    @Test
    void givenExistsBySku_whenExistProduct_thenReturnTrue() {
        final String sku = ProductFactory.sku;

        given(this.productRepository.existsBySku(sku)).willReturn(true);

        assertThat(this.productRepositoryAdapter.existsBySku(sku))
                .isTrue();

        verify(this.productRepository, times(1)).existsBySku(sku);
    }

    @Test
    void givenExistsBySku_whenExistProduct_thenReturnFalse() {
        final String sku = ProductFactory.sku;

        given(this.productRepository.existsBySku(sku)).willReturn(false);

        assertThat(this.productRepositoryAdapter.existsBySku(sku))
                .isFalse();

        verify(this.productRepository, times(1)).existsBySku(sku);
    }

    @Test
    void givenExistsBySku_whenErrorOccur_thenReturnThrowException() {
        final String sku = ProductFactory.sku;
        final MongoException exception = new MongoException("e");

        given(this.productRepository.existsBySku(sku)).willThrow(exception);

        assertThatThrownBy(() -> this.productRepositoryAdapter.existsBySku(sku))
                .isInstanceOf(MongoException.class)
                .isEqualTo(exception);

        verify(this.productRepository, times(1)).existsBySku(sku);
    }
}