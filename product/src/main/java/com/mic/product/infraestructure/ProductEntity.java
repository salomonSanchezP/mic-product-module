package com.mic.product.infraestructure;

import com.mic.product.domain.model.ProductTypeEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@Document(collection = "products")
public class ProductEntity {
    @Id
    private String sku;

    @NonNull
    private String name;

    @NonNull
    private String description;

    @NonNull
    private String internalCode;

    @NonNull
    private Double price;

    @NonNull
    private String brand;

    @NonNull
    private String color;

    @NonNull
    private ProductTypeEnum productType;
}
