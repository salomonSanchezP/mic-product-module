package com.mic.product.commons.utils;

import com.mic.product.commons.exception.ProductBadRequestException;
import com.mic.product.domain.model.ProductDO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Validations {

    public void validateProduct(ProductDO productDO) {
        if (productDO.getPrice() <= 0 || productDO.getPrice() >= 99999) {
            throw new ProductBadRequestException("Price must be greater than 0 and less than 99999");
        }
    }
}
