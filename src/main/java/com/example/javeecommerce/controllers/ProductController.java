package com.example.javeecommerce.controllers;

import com.example.javeecommerce.dtos.ApiResponse;
import com.example.javeecommerce.dtos.CreateOrUpdateProduct;
import com.example.javeecommerce.dtos.ProductDto;
import com.example.javeecommerce.entities.Product;
import com.example.javeecommerce.services.ProductService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Products")
public class ProductController {
    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping()
    public ApiResponse<ProductDto> addProduct(@RequestBody CreateOrUpdateProduct productDto){
        return productService.CreateProduct(productDto);
    }
    @GetMapping()
    public ApiResponse<List<ProductDto>> getAllProducts(){
        return productService.GetAllProducts();
    }
    @GetMapping("/{productId}")
    public ApiResponse<ProductDto> getProductById(@PathVariable Long productId){
        return productService.GetProductById(productId);
    }
    @DeleteMapping("/{productId}")
    public ApiResponse<ProductDto> deleteProduct(@PathVariable Long productId){
        return productService.DeleteProduct(productId);
    }
}
