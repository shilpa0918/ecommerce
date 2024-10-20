package com.docker.spring.app.mappers;

import com.docker.spring.app.domains.CategoryDTO;
import com.docker.spring.app.domains.ProductDTO;
import com.docker.spring.app.models.Category;
import com.docker.spring.app.models.Product;
import org.springframework.stereotype.Service;

@Service
public class DTOMapper {
    public ProductDTO productToProductDTO(Product product){
        if ( product == null ) {
            return null;
        }
        ProductDTO productDTO = new ProductDTO();
        productDTO.setPartNumber(product.getPartNumber());
        return productDTO;
    }
    public Product productDTOToProduct(ProductDTO productDTO){
        if ( productDTO == null ) {
            return null;
        }
        Product product = new Product();
        product.setPartNumber(productDTO.getPartNumber());
        return product;
    }

    public CategoryDTO categoryToCategoryDTO(Category category) {
        if(category == null) return null;
        return new CategoryDTO(category.getCategoryId(),category.getIdentifier(),category.getName(),category.getDescription(),category.getMarkForDelete(),category.getPublished());
    }

}