package com.mic.product.commons.utils;

import com.mic.product.commons.exception.ProductBadRequestException;
import com.mic.product.domain.model.ProductDO;
import com.mic.product.factory.ProductFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ValidationsTest {

    @InjectMocks
    private Validations validations;

    @Test
    void givenValidateProduct_whenValidPrice_thenResponseOk() {
        ProductDO product = ProductFactory.createProductDO();

        assertDoesNotThrow(() -> validations.validateProduct(product));
    }

    @Test
    void givenValidateProduct_whenPriceIsZero_thenResponseOk() {
        ProductDO product = ProductFactory.createProductDO();
        product.setPrice(0.0);

        assertThrows(ProductBadRequestException.class, () -> validations.validateProduct(product));
    }

    @Test
    void givenValidateProduct_whenPriceIsGreater9999_thenResponseOk() {
        ProductDO product = ProductFactory.createProductDO();
        product.setPrice(99999.0);

        assertThrows(ProductBadRequestException.class, () -> validations.validateProduct(product));
    }

}