package com.mic.product.factory;

import lombok.NonNull;

public class CheckNullValue {
  @SuppressWarnings("ConstantConditions")
  public static @NonNull <T> T sneakyNullReference() {
    return null;
  }
}
