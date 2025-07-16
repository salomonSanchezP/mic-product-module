package com.mic.product.rest;


import com.mic.product.domain.model.ProductDO;
import com.mic.product.infrastructure.domain.dto.ProductDTO;
import com.mic.product.infrastructure.domain.dto.ProductRequestDTO;
import com.mic.product.infrastructure.domain.dto.ProductUpdateRequestDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface ProductRestMapper {

    ProductDTO mapToProductDTO(ProductDO productDO);
    List<ProductDTO> mapToProductDTO(List<ProductDO> productDOs);
    ProductDO mapToProductDO(ProductRequestDTO productRequestDTO);
    ProductDO mapToProductDO(ProductUpdateRequestDTO productUpdateRequestDTO);
}
