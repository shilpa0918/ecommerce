package com.docker.spring.app.sevices;


import com.docker.spring.app.domains.AddCategoryRequest;
import com.docker.spring.app.domains.CategoryDTO;
import com.docker.spring.app.domains.UpdateCategoryRequest;

public interface CategoryService {
    public CategoryDTO addCategory(AddCategoryRequest addCategoryRequest);
    public CategoryDTO getCategoryByCategoryId(String categoryId);
    public void deleteCategory(String categoryId);
    public CategoryDTO updateCategory(UpdateCategoryRequest updateCategoryRequest);
    public CategoryDTO getCategoryByCategoryName(String categoryId);
}
