package ru.solarev.core.coreservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.solarev.core.coreservice.converter.ProductConverter;
import ru.solarev.core.coreservice.dto.ProductDto;
import ru.solarev.core.coreservice.model.Product;
import ru.solarev.core.coreservice.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/products")
@Tag(name = "Product Controller", description = "Core API")
public class ProductController {

    private final ProductService productService;
    private final ProductConverter productConverter;

    @GetMapping("/")
    @Operation(summary = "Show all products")
    public List<ProductDto> showAllProducts() {
        return productService.getAllProducts().stream().map(productConverter::entityToDto).
                collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Show one product")
    public ProductDto showById(@PathVariable("id") Long id) {
        return productConverter.entityToDto(productService.getProductById(id));
    }

    @PostMapping("/create")
    @Operation(summary = "Create product")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        Product product = productService.createProduct(productConverter.dtoToEntity(productDto));
        return productConverter.entityToDto(product);
    }

    @PutMapping("/update")
    @Operation(summary = "Update product")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        productService.updateProduct(productConverter.dtoToEntity(productDto));
        return productDto;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete product")
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }

}
