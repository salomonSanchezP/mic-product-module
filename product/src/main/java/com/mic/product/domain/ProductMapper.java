package com.mic.product.domain;

import com.mic.product.domain.model.ProductDO;
import com.mic.product.infraestructure.ProductEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL)
public interface ProductMapper {

    ProductDO mapToProductDO(ProductEntity productEntity);

    List<ProductDO> mapToProductDOList(List<ProductEntity> productEntityList);
}
