package ru.solarev.core.coreservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.solarev.core.coreservice.model.Product;
import ru.solarev.core.coreservice.repository.ProductRepository;
import ru.solarev.core.coreservice.repository.spec.ProductSpecifications;
import ru.solarev.core.coreservice.service.ProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Page<Product> searchProducts(Integer minPrice, Integer maxPrice,
                                        String partTitle, Integer page, Integer pageSize) {
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpecifications.priceLessThanOrEqualsThan(maxPrice));
        }
        if (partTitle != null) {
            spec = spec.and(ProductSpecifications.titleLike(partTitle));
        }
        return productRepository.findAll(spec, PageRequest.of(page - 1, pageSize));
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "ProductService::getProductById", key = "#id")
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "ProductService::getProductByTitle", key = "#title")
    public Product getProductByTitle(String title) {
        return null;
    }

    @Transactional
    @Caching(cacheable = {
            @Cacheable(value = "ProductService::getProductById",
                    condition = "#product.id!=null",
                    key = "#product.id"),
            @Cacheable(value = "ProductService::getProductByTitle",
                    condition = "#product.title!=null",
                    key = "#product.title")
    })
    public Product createProduct(Product product) {
        productRepository.save(product);
        return productRepository.findByTitle(product.getTitle());
    }

    @Transactional
    @Caching(put = {
            @CachePut(value = "ProductService::getProductById",
                    key = "#product.id"),
            @CachePut(value = "ProductService::getProductByTitle",
                    key = "#product.title")
    })
    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    @Transactional
    @CacheEvict(value = "ProductService::getProductById", key = "#id")
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
