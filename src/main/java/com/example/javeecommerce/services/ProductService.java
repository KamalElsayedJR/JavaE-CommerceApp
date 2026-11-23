package com.example.javeecommerce.services;

import com.example.javeecommerce.Repositories.ProductRepository;
import com.example.javeecommerce.dtos.ApiResponse;
import com.example.javeecommerce.dtos.ProductDto;
import com.example.javeecommerce.entities.Product;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    @Autowired
    ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Transactional
    public ApiResponse<ProductDto> CreateProduct(ProductDto product){
        if (product == null){
            List<String> errors = new ArrayList<>();
            errors.add("Product is null");
            return new ApiResponse<>(false,300,null,"Cant added a empty product",errors);
        }
        Product newProduct = new Product();
        newProduct.setName(product.name());
        newProduct.setDescription(product.description());
        newProduct.setPrice(product.price());
        productRepository.save(newProduct);
        ProductDto productDto = new ProductDto(
                newProduct.getId(),
                newProduct.getName(),
                newProduct.getDescription(),
                newProduct.getPrice()
        );
        return new ApiResponse<>(true,201,productDto,"Product Added Successfully",null);
    }
}
