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
 * Response product data
 */

@Schema(name = "Product", description = "Response product data")
@JsonTypeName("Product")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-07-16T16:30:05.008659600-05:00[America/Lima]", comments = "Generator version: 7.13.0")
public class ProductDTO {

  private String sku;

  private String name;

  private String description;

  private Double price;

  private String brand;

  private String color;

  private ProductTypeDTO productType;

  public ProductDTO() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public ProductDTO(String sku, String name, String description, Double price, String brand, String color, ProductTypeDTO productType) {
    this.sku = sku;
    this.name = name;
    this.description = description;
    this.price = price;
    this.brand = brand;
    this.color = color;
    this.productType = productType;
  }

  public ProductDTO sku(String sku) {
    this.sku = sku;
    return this;
  }

  /**
   * unique identifier of the product
   * @return sku
   */
  @NotNull 
  @Schema(name = "sku", example = "SKU123456", description = "unique identifier of the product", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("sku")
  public String getSku() {
    return sku;
  }

  public void setSku(String sku) {
    this.sku = sku;
  }

  public ProductDTO name(String name) {
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

  public ProductDTO description(String description) {
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

  public ProductDTO price(Double price) {
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

  public ProductDTO brand(String brand) {
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

  public ProductDTO color(String color) {
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

  public ProductDTO productType(ProductTypeDTO productType) {
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
    ProductDTO product = (ProductDTO) o;
    return Objects.equals(this.sku, product.sku) &&
        Objects.equals(this.name, product.name) &&
        Objects.equals(this.description, product.description) &&
        Objects.equals(this.price, product.price) &&
        Objects.equals(this.brand, product.brand) &&
        Objects.equals(this.color, product.color) &&
        Objects.equals(this.productType, product.productType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sku, name, description, price, brand, color, productType);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductDTO {\n");
    sb.append("    sku: ").append(toIndentedString(sku)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
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

