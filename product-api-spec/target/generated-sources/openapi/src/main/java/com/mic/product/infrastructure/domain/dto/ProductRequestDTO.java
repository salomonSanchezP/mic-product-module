package com.mic.product.infrastructure.domain.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonValue;
import com.mic.product.infrastructure.domain.dto.ProductTypeDTO;
import org.springframework.lang.Nullable;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Request to create a product
 */

@Schema(name = "ProductRequest", description = "Request to create a product")
@JsonTypeName("ProductRequest")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-07-16T16:30:05.008659600-05:00[America/Lima]", comments = "Generator version: 7.13.0")
public class ProductRequestDTO {

  private String name;

  private String description;

  private Double price;

  private String internalCode;

  private String brand;

  private String color;

  private ProductTypeDTO productType;

  public ProductRequestDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ProductRequestDTO(String name, String description, Double price, String internalCode, String brand, String color, ProductTypeDTO productType) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.internalCode = internalCode;
    this.brand = brand;
    this.color = color;
    this.productType = productType;
  }

  public ProductRequestDTO name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the product
   * @return name
   */
  @NotNull 
  @Schema(name = "name", example = "Laptop", description = "Name of the product", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ProductRequestDTO description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Description of the product
   * @return description
   */
  @NotNull 
  @Schema(name = "description", example = "High performance laptop with 16GB RAM and 512GB SSD", description = "Description of the product", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ProductRequestDTO price(Double price) {
    this.price = price;
    return this;
  }

  /**
   * Price of the product
   * @return price
   */
  @NotNull 
  @Schema(name = "price", description = "Price of the product", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("price")
  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public ProductRequestDTO internalCode(String internalCode) {
    this.internalCode = internalCode;
    return this;
  }

  /**
   * Internal code of the product
   * @return internalCode
   */
  @NotNull 
  @Schema(name = "internalCode", example = "INT123456", description = "Internal code of the product", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("internalCode")
  public String getInternalCode() {
    return internalCode;
  }

  public void setInternalCode(String internalCode) {
    this.internalCode = internalCode;
  }

  public ProductRequestDTO brand(String brand) {
    this.brand = brand;
    return this;
  }

  /**
   * Brand of the product
   * @return brand
   */
  @NotNull 
  @Schema(name = "brand", example = "BrandName", description = "Brand of the product", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("brand")
  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public ProductRequestDTO color(String color) {
    this.color = color;
    return this;
  }

  /**
   * Color of the product
   * @return color
   */
  @NotNull 
  @Schema(name = "color", example = "Black", description = "Color of the product", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("color")
  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public ProductRequestDTO productType(ProductTypeDTO productType) {
    this.productType = productType;
    return this;
  }

  /**
   * Get productType
   * @return productType
   */
  @NotNull @Valid 
  @Schema(name = "productType", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("productType")
  public ProductTypeDTO getProductType() {
    return productType;
  }

  public void setProductType(ProductTypeDTO productType) {
    this.productType = productType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductRequestDTO productRequest = (ProductRequestDTO) o;
    return Objects.equals(this.name, productRequest.name) &&
        Objects.equals(this.description, productRequest.description) &&
        Objects.equals(this.price, productRequest.price) &&
        Objects.equals(this.internalCode, productRequest.internalCode) &&
        Objects.equals(this.brand, productRequest.brand) &&
        Objects.equals(this.color, productRequest.color) &&
        Objects.equals(this.productType, productRequest.productType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description, price, internalCode, brand, color, productType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductRequestDTO {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    internalCode: ").append(toIndentedString(internalCode)).append("\n");
    sb.append("    brand: ").append(toIndentedString(brand)).append("\n");
    sb.append("    color: ").append(toIndentedString(color)).append("\n");
    sb.append("    productType: ").append(toIndentedString(productType)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

