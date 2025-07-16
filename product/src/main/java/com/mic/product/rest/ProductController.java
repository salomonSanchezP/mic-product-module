package com.mic.product.rest;



import com.mic.product.application.ProductService;
import com.mic.product.domain.model.ProductDO;
import com.mic.product.infrastructure.domain.dto.ProductDTO;
import com.mic.product.infrastructure.domain.dto.ProductRequestDTO;
import com.mic.product.infrastructure.domain.dto.ProductUpdateRequestDTO;
import com.mic.product.infrastructure.rest.service.ProductApi;
import com.mic.product.commons.utils.Validations;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@Slf4j
@RestController
@AllArgsConstructor
public class ProductController implements ProductApi {

    private ProductService productService;

    private ProductRestMapper productRestMapper;

    private Validations validations;

    @Override
    public ResponseEntity<ProductDTO> createProduct(ProductRequestDTO productRequestDTO) {
        ProductDTO productDTO;
        ProductDO productDO = productRestMapper.mapToProductDO(productRequestDTO);
        this.validations.validateProduct(productDO);
        ProductDO newProduct = productService.createProduct(productDO);
        productDTO = productRestMapper.mapToProductDTO(newProduct);
        return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteProduct(String id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<ProductDTO>> findProducts() {
        List<ProductDO> productDOs = productService.getAllProducts();
        List<ProductDTO> productDTOs = productRestMapper.mapToProductDTO(productDOs);
        return new ResponseEntity<>(productDTOs, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductDTO> findProduct(String id) {
        ProductDO productDO = productService.getProductBySku(id);
        ProductDTO productDTO = productRestMapper.mapToProductDTO(productDO);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductDTO> patchProduct(String id, ProductUpdateRequestDTO productRequestDTO) {
        ProductDTO productDTO;
        ProductDO productDO = productRestMapper.mapToProductDO(productRequestDTO);
        this.validations.validateProduct(productDO);
        ProductDO newProduct = productService.patchProduct(id, productDO);
        productDTO = productRestMapper.mapToProductDTO(newProduct);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductDTO> updateProduct(String id, ProductUpdateRequestDTO productRequestDTO) {
        ProductDTO productDTO;
        ProductDO productDO = productRestMapper.mapToProductDO(productRequestDTO);
        this.validations.validateProduct(productDO);
        ProductDO newProduct = productService.updateProduct(id, productDO);
        productDTO = productRestMapper.mapToProductDTO(newProduct);
        return new ResponseEntity<>(productDTO, HttpStatus.OK);
    }
}
