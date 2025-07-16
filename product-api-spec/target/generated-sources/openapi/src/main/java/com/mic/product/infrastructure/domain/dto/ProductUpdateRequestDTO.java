package com.mic.product.infrastructure.domain.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
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

@Schema(name = "ProductUpdateRequest", description = "Request to create a product")
@JsonTypeName("ProductUpdateRequest")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-07-16T16:30:05.008659600-05:00[America/Lima]", comments = "Generator version: 7.13.0")
public class ProductUpdateRequestDTO {

  private @Nullable String name;

  private @Nullable String description;

  private @Nullable Double price;

  private @Nullable String color;

  public ProductUpdateRequestDTO name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the product
   * @return name
   */
  
  @Schema(name = "name", example = "Laptop", description = "Name of the product", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ProductUpdateRequestDTO description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Description of the product
   * @return description
   */
  
  @Schema(name = "description", example = "High performance laptop with 16GB RAM and 512GB SSD", description = "Description of the product", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ProductUpdateRequestDTO price(Double price) {
    this.price = price;
    return this;
  }

  /**
   * Price of the product
   * @return price
   */
  
  @Schema(name = "price", description = "Price of the product", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("price")
  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public ProductUpdateRequestDTO color(String color) {
    this.color = color;
    return this;
  }

  /**
   * Color of the product
   * @return color
   */
  
  @Schema(name = "color", example = "Black", description = "Color of the product", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("color")
  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductUpdateRequestDTO productUpdateRequest = (ProductUpdateRequestDTO) o;
    return Objects.equals(this.name, productUpdateRequest.name) &&
        Objects.equals(this.description, productUpdateRequest.description) &&
        Objects.equals(this.price, productUpdateRequest.price) &&
        Objects.equals(this.color, productUpdateRequest.color);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description, price, color);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductUpdateRequestDTO {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    color: ").append(toIndentedString(color)).append("\n");
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

