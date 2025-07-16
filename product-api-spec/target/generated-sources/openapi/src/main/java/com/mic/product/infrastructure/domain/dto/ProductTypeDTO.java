package com.mic.product.infrastructure.domain.dto;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Gets or Sets ProductType
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-07-16T16:30:05.008659600-05:00[America/Lima]", comments = "Generator version: 7.13.0")
public enum ProductTypeDTO {
  
  COMPUTER("COMPUTER"),
  
  MOBILE("MOBILE"),
  
  COMPONENT("COMPONENT"),
  
  PERIPHERAL("PERIPHERAL");

  private final String value;

  ProductTypeDTO(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static ProductTypeDTO fromValue(String value) {
    for (ProductTypeDTO b : ProductTypeDTO.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }
}

