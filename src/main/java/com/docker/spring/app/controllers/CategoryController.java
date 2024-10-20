package com.docker.spring.app.controllers;


import com.docker.spring.app.domains.AddCategoryRequest;
import com.docker.spring.app.domains.CategoryDTO;
import com.docker.spring.app.domains.UpdateCategoryRequest;
import com.docker.spring.app.sevices.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category/api/v1")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping(path = "")
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody AddCategoryRequest addCategoryRequest){
        CategoryDTO category = categoryService.addCategory(addCategoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    @GetMapping(path = "/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategoryByCategoryId(@PathVariable String categoryId){
        CategoryDTO category = categoryService.getCategoryByCategoryId(categoryId);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }
    @GetMapping(path = "/{categoryName}")
    public ResponseEntity<CategoryDTO> getCategoryByCategoryName(@PathVariable String categoryId){
        CategoryDTO category = categoryService.getCategoryByCategoryName(categoryId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(category);
    }

    @PutMapping(path = "")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody UpdateCategoryRequest updateCategoryRequest){
        CategoryDTO category = categoryService.updateCategory(updateCategoryRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(category);
    }

    @DeleteMapping(path = "/{categoryId}")
    public ResponseEntity deleteCategory(@PathVariable String categoryId){
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
