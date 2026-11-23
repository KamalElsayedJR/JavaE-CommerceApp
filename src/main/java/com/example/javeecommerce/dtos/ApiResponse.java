package com.example.javeecommerce.dtos;

import java.util.List;
import java.util.Optional;

public record ApiResponse<T>(Boolean isSuccess, int statusCode, T data,
                             String messages ,List<String> errors) {
}
