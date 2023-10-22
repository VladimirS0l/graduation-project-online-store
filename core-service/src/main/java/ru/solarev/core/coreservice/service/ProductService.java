package ru.solarev.core.coreservice.service;

import ru.solarev.core.coreservice.model.Product;

import java.util.List;

public interface ProductService {


    public List<Product> getAllProducts();

    public Product getProductById(Long id);
    public Product getProductByTitle(String title);

    public Product createProduct(Product product);

    public void updateProduct(Product product);

    public void deleteProduct(Long id);
}
