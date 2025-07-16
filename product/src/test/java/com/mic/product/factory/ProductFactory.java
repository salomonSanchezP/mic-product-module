package com.mic.product.factory;

import com.mic.product.domain.model.ProductDO;
import com.mic.product.domain.model.ProductTypeEnum;
import com.mic.product.infraestructure.ProductEntity;
import com.mic.product.infrastructure.domain.dto.ProductDTO;
import com.mic.product.infrastructure.domain.dto.ProductRequestDTO;
import com.mic.product.infrastructure.domain.dto.ProductTypeDTO;
import com.mic.product.infrastructure.domain.dto.ProductUpdateRequestDTO;

import java.util.List;

public class ProductFactory {

    public final static String sku = "BRANDX-INT123-COMPUTER";

    public static ProductDO createProductDO() {
        return ProductDO.builder()
                .sku(sku)
                .name("Test Product")
                .productType(ProductTypeEnum.valueOf("COMPUTER"))
                .price(100.0)
                .internalCode("INT123")
                .brand("BrandX")
                .description("A test product")
                .color("Black")
                .build();
    }

    public static ProductEntity createProductEntity() {
        return ProductEntity.builder()
                .sku(sku)
                .name("Test Product")
                .productType(ProductTypeEnum.valueOf("COMPUTER"))
                .price(100.0)
                .internalCode("INT123")
                .brand("BrandX")
                .description("A test product")
                .color("Black")
                .build();
    }

    public static List<ProductEntity> createProductEntityList() {
        return List.of(createProductEntity());
    }

    public static List<ProductDO> createProductDOList() {
        return List.of(createProductDO());
    }

    public static List<ProductDTO> createProductDTOList() {
        return List.of(createProductDTO());
    }

    public static ProductRequestDTO CreateProductRequestDTO() {
        ProductRequestDTO productRequestDTO = new ProductRequestDTO();
        productRequestDTO.setName("Test Product");
        productRequestDTO.setProductType(ProductTypeDTO.valueOf("COMPUTER"));
        productRequestDTO.setPrice(100.0);
        productRequestDTO.setInternalCode("INT123");
        productRequestDTO.setBrand("BrandX");
        productRequestDTO.setDescription("A test product");
        productRequestDTO.setColor("Black");
        return productRequestDTO;
    }

    public static ProductDTO createProductDTO() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setSku(sku);
        productDTO.setName("Test Product");
        productDTO.setProductType(ProductTypeDTO.valueOf("COMPUTER"));
        productDTO.setPrice(100.0);
        productDTO.setBrand("BrandX");
        productDTO.setDescription("A test product");
        productDTO.setColor("Black");
        return productDTO;
    }


    public static ProductUpdateRequestDTO createProductUpdateRequestDTO() {
        ProductUpdateRequestDTO productUpdateRequestDTO = new ProductUpdateRequestDTO();
        productUpdateRequestDTO.setName("Updated Test Product");
        productUpdateRequestDTO.setPrice(120.0);
        productUpdateRequestDTO.setDescription("An updated test product");
        productUpdateRequestDTO.setColor("Black");
        return productUpdateRequestDTO;
    }

    public static ProductDO updateProductDO() {
        return ProductDO.builder()
                .sku(sku)
                .name("Updated Test Product")
                .productType(ProductTypeEnum.valueOf("COMPUTER"))
                .price(120.0)
                .internalCode("INT123")
                .brand("BrandX")
                .description("An updated test product")
                .color("Black")
                .build();
    }

    public static ProductDTO updateProductDTO() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setSku(sku);
        productDTO.setName("Updated Test Product");
        productDTO.setProductType(ProductTypeDTO.valueOf("COMPUTER"));
        productDTO.setPrice(120.0);
        productDTO.setBrand("BrandX");
        productDTO.setDescription("An updated test product");
        productDTO.setColor("Black");
        return productDTO;
    }
}
