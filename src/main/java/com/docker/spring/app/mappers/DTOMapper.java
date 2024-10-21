package com.docker.spring.app.mappers;

import com.docker.spring.app.domains.CategoryDTO;
import com.docker.spring.app.domains.ProductDTO;
import com.docker.spring.app.models.Category;
import com.docker.spring.app.models.Product;
import org.springframework.stereotype.Service;

@Service
public class DTOMapper {
    public ProductDTO productToProductDTO(Product product) {
        if (product == null) {
            return null;
        }
        return new ProductDTO(product.getProductId(), product.getPartNumber(), product.getName(),
                product.getDescription(), product.getInventory(), product.getOfferPrice(), product.getListPrice(),
                product.getBrand());
    }

    public Product productDTOToProduct(ProductDTO productDTO) {
        if (productDTO == null) {
            return null;
        }
        Product product = new Product();
        product.setPartNumber(productDTO.getPartNumber());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setInventory(productDTO.getInventory());
        product.setOfferPrice(productDTO.getOfferPrice());
        product.setListPrice(productDTO.getListPrice());
        product.setBrand(productDTO.getBrand());
        return product;
    }

    public CategoryDTO categoryToCategoryDTO(Category category) {
        if (category == null) return null;
        return new CategoryDTO(category.getCategoryId(), category.getIdentifier(), category.getName(), category.getDescription(), category.getMarkForDelete(), category.getPublished());
    }

}