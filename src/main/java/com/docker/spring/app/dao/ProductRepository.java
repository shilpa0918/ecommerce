package com.docker.spring.app.dao;

import com.docker.spring.app.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    public Optional<Product> findByPartNumber(String partNumber);
}
