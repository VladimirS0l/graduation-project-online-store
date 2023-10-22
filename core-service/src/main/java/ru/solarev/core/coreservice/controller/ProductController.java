package ru.solarev.core.coreservice.controller;

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
public class ProductController {

    private final ProductService productService;
    private final ProductConverter productConverter;

    @GetMapping("/")
    public List<ProductDto> showAllProducts() {
        return productService.getAllProducts().stream().map(productConverter::entityToDto).
                collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto showById(@PathVariable("id") Long id) {
        return productConverter.entityToDto(productService.getProductById(id));
    }

    @PostMapping("/create")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        Product product = productService.createProduct(productConverter.dtoToEntity(productDto));
        return productConverter.entityToDto(product);
    }

    @PutMapping("/update")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        productService.updateProduct(productConverter.dtoToEntity(productDto));
        return productDto;
    }

    @DeleteMapping("/delete")
    public void deleteProduct(@RequestBody Long id) {
        productService.deleteProduct(id);
    }

}
