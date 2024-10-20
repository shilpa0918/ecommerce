package com.docker.spring.app.sevices.impl;

import com.docker.spring.app.dao.CategoryRepository;
import com.docker.spring.app.domains.AddCategoryRequest;
import com.docker.spring.app.domains.CategoryDTO;
import com.docker.spring.app.domains.UpdateCategoryRequest;
import com.docker.spring.app.mappers.DTOMapper;
import com.docker.spring.app.models.Category;
import com.docker.spring.app.sevices.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    DTOMapper mapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @Override
    public CategoryDTO addCategory(AddCategoryRequest addCategoryRequest) {
        Category categoryToAdd = categoryRepository.saveAndFlush(Category.builder()
                        .name(addCategoryRequest.getName())
                        .description(addCategoryRequest.getDescription())
                        .markForDelete(0)
                        .published(1)
                        .identifier(addCategoryRequest.getIdentifier())
                .build());
        return mapper.categoryToCategoryDTO(categoryToAdd);
    }

    @Override
    public CategoryDTO getCategoryByCategoryId(String categoryId) {
        Category category = categoryRepository.findById(Long.valueOf(categoryId)).orElseThrow();
        return mapper.categoryToCategoryDTO(category);
    }

    @Override
    public CategoryDTO getCategoryByCategoryName(String categoryName) {
        Category category = categoryRepository.findByName(categoryName).orElseThrow();
        return mapper.categoryToCategoryDTO(category);
    }

    @Override
    public void deleteCategory(String categoryId) {
        Category category = categoryRepository.findById(Long.valueOf(categoryId)).orElseThrow();
        categoryRepository.deleteById(category.getCategoryId());
    }

    @Override
    public CategoryDTO updateCategory(UpdateCategoryRequest updateCategoryRequest) {
        Category category = categoryRepository.findById(updateCategoryRequest.getCategoryId()).orElseThrow();
        Category categoryToUpdate = categoryRepository.saveAndFlush(Category.builder()
                        .name(updateCategoryRequest.getName())
                        .categoryId(updateCategoryRequest.getCategoryId())
                        .identifier(updateCategoryRequest.getIdentifier())
                        .published(updateCategoryRequest.getPublished())
                        .markForDelete(updateCategoryRequest.getMarkForDelete())
                        .description(updateCategoryRequest.getDescription())
                .build());
        return mapper.categoryToCategoryDTO(categoryToUpdate);
    }

}
