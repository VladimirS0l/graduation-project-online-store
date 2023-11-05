package ru.solarev.core.coreservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.solarev.core.coreservice.converter.ProductMapper;
import ru.solarev.core.coreservice.dto.ProductDto;
import ru.solarev.core.coreservice.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/products")
@Tag(name = "Product Controller", description = "Core API")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @GetMapping("/")
    @Operation(summary = "Show all products")
    public List<ProductDto> showAllProducts() {
        return productMapper.toDto(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Show one product")
    public ProductDto showById(@PathVariable("id") Long id) {
        return productMapper.toDto(productService.getProductById(id));
    }

    @PostMapping("/create")
    @Operation(summary = "Create product")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        productService.createProduct(productMapper.toEntity(productDto));
        return productDto;
    }

    @PutMapping("/update")
    @Operation(summary = "Update product")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        productService.updateProduct(productMapper.toEntity(productDto));
        return productDto;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete product")
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }

}
