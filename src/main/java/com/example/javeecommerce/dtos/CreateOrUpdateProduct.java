package com.example.javeecommerce.dtos;

import java.math.BigDecimal;

public record CreateOrUpdateProduct(String name, String description, BigDecimal price) {
}
