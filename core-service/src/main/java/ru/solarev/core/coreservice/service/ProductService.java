package ru.solarev.core.coreservice.service;

import org.springframework.data.domain.Page;
import ru.solarev.core.coreservice.model.Product;

import java.util.List;

public interface ProductService {

    public Page<Product> searchProducts(Integer minPrice, Integer maxPrice,
                                        String partTitle, Integer page,
                                        Integer pageSize);
    public List<Product> getAllProducts();

    public Product getProductById(Long id);
    public Product getProductByTitle(String title);

    public Product createProduct(Product product);

    public void updateProduct(Product product);

    public void deleteProduct(Long id);
}
