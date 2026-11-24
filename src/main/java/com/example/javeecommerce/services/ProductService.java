package com.example.javeecommerce.services;

import com.example.javeecommerce.Repositories.ProductRepository;
import com.example.javeecommerce.dtos.ApiResponse;
import com.example.javeecommerce.dtos.CreateOrUpdateProduct;
import com.example.javeecommerce.dtos.ProductDto;
import com.example.javeecommerce.entities.Product;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    //Create new Product
    @Transactional
    public ApiResponse<ProductDto> CreateProduct(CreateOrUpdateProduct product){
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

    //Get All Products
    public ApiResponse<List<ProductDto>> GetAllProducts(){
        List<ProductDto> products =  productRepository.findAll().stream().map(product -> new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        )).toList();
        return new ApiResponse<>(true,200,products,"All Products Retrived Successfully",null);

    }

    //Get Products By id
    public ApiResponse<ProductDto> GetProductById(Long productId){
        List<String> errors = new ArrayList<>();

        if (productId == null) {
            errors.add("Product ID is required");
            return new ApiResponse<>(false, 400, null, "Product ID cannot be null", errors);
        }

        Optional<Product> product = productRepository.findById(productId);

        if (product.isEmpty()) {
            errors.add("Product not found");
            return new ApiResponse<>(false, 404, null, "Product not found", errors);
        }
         ProductDto productDto = new ProductDto(
            product.get().getId(),
            product.get().getName(),
            product.get().getDescription(),
            product.get().getPrice()
        );
        return new ApiResponse<ProductDto>(true,200,productDto,"Product Found",null);
    }
    @Transactional
    // Delete product by id
    public ApiResponse<ProductDto> DeleteProduct(Long productId) {

        List<String> errors = new ArrayList<>();

        if (productId == null) {
            errors.add("Product ID is required");
            return new ApiResponse<>(false, 400, null, "Product ID cannot be null", errors);
        }

        Optional<Product> product = productRepository.findById(productId);

        if (product.isEmpty()) {
            errors.add("Product not found");
            return new ApiResponse<>(false, 404, null, "Product not found", errors);
        }

        productRepository.delete(product.get());

        return new ApiResponse<>(true, 200, null, "Product deleted successfully", null);
    }



}
