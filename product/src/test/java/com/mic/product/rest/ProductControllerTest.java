package com.mic.product.rest;

import com.mic.product.application.ProductService;
import com.mic.product.commons.exception.ProductAlreadyExistsException;
import com.mic.product.commons.utils.Validations;
import com.mic.product.domain.model.ProductDO;
import com.mic.product.factory.ProductFactory;
import com.mic.product.infrastructure.domain.dto.ProductDTO;
import com.mic.product.infrastructure.domain.dto.ProductRequestDTO;
import com.mic.product.infrastructure.domain.dto.ProductUpdateRequestDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.http.HttpStatus.CREATED;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private ProductRestMapper productRestMapper;

    @Mock
    private Validations validations;

    @InjectMocks
    private ProductController productController;

    @Test
    void givenCreateProduct_whenCreateIsOK_thenReturnProduct() {
        ProductRequestDTO productRequestDTO =ProductFactory.CreateProductRequestDTO();
        ProductDO productDO = ProductFactory.createProductDO();
        ProductDTO productDTO = ProductFactory.createProductDTO();

        given(productRestMapper.mapToProductDO(productRequestDTO)).willReturn(productDO);
        doNothing().when(validations).validateProduct(productDO);
        given(productService.createProduct(productDO)).willReturn(productDO);
        given(productRestMapper.mapToProductDTO(productDO)).willReturn(productDTO);

        ResponseEntity<ProductDTO> response = productController.createProduct(productRequestDTO);

        assertThat(response.getStatusCode())
                .isEqualTo(CREATED);

        assertThat(response)
                .isNotNull()
                .extracting(HttpEntity::getBody)
                .isEqualTo(productDTO);
    }

    @Test
    void givenCreateProduct_whenErrorOnCreate_thenThrowException() {
        final ProductRequestDTO productRequestDTO =ProductFactory.CreateProductRequestDTO();
        final ProductDO productDO = ProductFactory.createProductDO();
        final ProductAlreadyExistsException exception = new ProductAlreadyExistsException("Product already exists");

        given(productRestMapper.mapToProductDO(productRequestDTO)).willReturn(productDO);
        doNothing().when(validations).validateProduct(productDO);
        given(productService.createProduct(productDO)).willThrow(exception);

        assertThatThrownBy(() -> this.productController.createProduct(productRequestDTO))
                .isInstanceOf(ProductAlreadyExistsException.class)
                .isEqualTo(exception);
    }

    @Test
    void givenDeleteProduct_whenDeleteIsOk_thenReturnOK() {
        String sku = ProductFactory.sku;

        doNothing().when(productService).deleteProduct(sku);

        ResponseEntity<Void> response = productController.deleteProduct(sku);

        assertThat(response.getStatusCode())
                .isEqualTo(ResponseEntity.noContent().build().getStatusCode());

        assertThat(response.getBody()).isNull();
    }

    @Test
    void givenFindProducts_whenAllisOk_thenReturnProducts() {
        List<ProductDO> productDOs = ProductFactory.createProductDOList();
        List<ProductDTO> productDTOs = ProductFactory.createProductDTOList();

        given(productService.getAllProducts()).willReturn(productDOs);
        given(productRestMapper.mapToProductDTO(productDOs)).willReturn(productDTOs);

        ResponseEntity<List<ProductDTO>> response = productController.findProducts();

        assertThat(response.getStatusCode())
                .isEqualTo(ResponseEntity.ok().build().getStatusCode());

        assertThat(response.getBody())
                .isNotNull()
                .containsExactly(productDTOs.toArray(new ProductDTO[0]));
    }

    @Test
    void givenFindProduct_whenAllisOK_thenReturnProduct() {
        String sku = ProductFactory.sku;
        ProductDO productDO = ProductFactory.createProductDO();
        ProductDTO productDTO = ProductFactory.createProductDTO();

        given(productService.getProductBySku(sku)).willReturn(productDO);
        given(productRestMapper.mapToProductDTO(productDO)).willReturn(productDTO);

        ResponseEntity<ProductDTO> response = productController.findProduct(sku);

        assertThat(response.getStatusCode())
                .isEqualTo(ResponseEntity.ok().build().getStatusCode());

        assertThat(response.getBody())
                .isNotNull()
                .isEqualTo(productDTO);
    }

    @Test
    void givenPatchProduct_whenAllIsOk_thenReturnOk() {
        String sku = ProductFactory.sku;
        ProductUpdateRequestDTO productRequestDTO = ProductFactory.createProductUpdateRequestDTO();
        ProductDO productDO = ProductFactory.updateProductDO();
        ProductDTO productDTO = ProductFactory.updateProductDTO();

        given(productRestMapper.mapToProductDO(productRequestDTO)).willReturn(productDO);
        doNothing().when(validations).validateProduct(productDO);
        given(productService.patchProduct(sku, productDO)).willReturn(productDO);
        given(productRestMapper.mapToProductDTO(productDO)).willReturn(productDTO);

        ResponseEntity<ProductDTO> response = productController.patchProduct(sku, productRequestDTO);

        assertThat(response.getStatusCode())
                .isEqualTo(ResponseEntity.ok().build().getStatusCode());

        assertThat(response.getBody())
                .isNotNull()
                .isEqualTo(productDTO);
    }

    @Test
    void givenUpdateProduct_whenAllisOk_thenReturnOK() {
        String sku = ProductFactory.sku;
        ProductUpdateRequestDTO productRequestDTO = ProductFactory.createProductUpdateRequestDTO();
        ProductDO productDO = ProductFactory.updateProductDO();
        ProductDTO productDTO = ProductFactory.updateProductDTO();

        given(productRestMapper.mapToProductDO(productRequestDTO)).willReturn(productDO);
        doNothing().when(validations).validateProduct(productDO);
        given(productService.updateProduct(sku, productDO)).willReturn(productDO);
        given(productRestMapper.mapToProductDTO(productDO)).willReturn(productDTO);

        ResponseEntity<ProductDTO> response = productController.updateProduct(sku, productRequestDTO);

        assertThat(response.getStatusCode())
                .isEqualTo(ResponseEntity.ok().build().getStatusCode());

        assertThat(response.getBody())
                .isNotNull()
                .isEqualTo(productDTO);
    }
}