package ru.solarev.core.coreservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.solarev.api.apiservice.core.ProductDto;
import ru.solarev.core.coreservice.converter.ProductMapper;
import ru.solarev.core.coreservice.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
@Tag(name = "Товары", description = "Методы для работы с товарами")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @Operation(summary = "Запрос на получение страницы товаров")
    @GetMapping
    public Page<ProductDto> searchProducts(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_price", required = false) Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "title_part", required = false) String titlePart,
            @RequestParam(name = "page_size", defaultValue = "9") Integer pageSize) {
        if (page < 1) {
            page = 1;
        }

        return productMapper.toDto(productService
                .searchProducts(minPrice, maxPrice, titlePart, page, pageSize));
    }

    @Operation(summary = "Показать все товары")
    @GetMapping("/")
    public List<ProductDto> showAllProducts() {
        return productMapper.toDto(productService.getAllProducts());
    }

    @Operation(summary = "Показать товар по ID")
    @GetMapping("/{id}")
    public ProductDto showById(@PathVariable("id") Long id) {
        return productMapper.toDto(productService.getProductById(id));
    }

    @Operation(summary = "Добавить товар")
    @PostMapping("/create")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        productService.createProduct(productMapper.toEntity(productDto));
        return productDto;
    }

    @Operation(summary = "Обновить товар")
    @PutMapping("/update")
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        productService.updateProduct(productMapper.toEntity(productDto));
        return productDto;
    }

    @Operation(summary = "Удалить товар по ID")
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }

}
