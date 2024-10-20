package com.docker.spring.app.dao;

import com.docker.spring.app.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    public Optional<Category> findByName(String categoryName);
}
