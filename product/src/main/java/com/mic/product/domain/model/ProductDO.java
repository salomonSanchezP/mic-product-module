package com.mic.product.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDO {
    private String sku;

    private String name;

    private String description;
    private String internalCode;

    private Double price;

    private String brand;

    private String color;

    private ProductTypeEnum productType;
}
