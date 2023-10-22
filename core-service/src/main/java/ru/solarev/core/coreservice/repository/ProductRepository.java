package ru.solarev.core.coreservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.solarev.core.coreservice.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByTitle(String title);
}
