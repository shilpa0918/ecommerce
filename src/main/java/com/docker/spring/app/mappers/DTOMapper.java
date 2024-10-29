package com.docker.spring.app.mappers;

import com.docker.spring.app.domains.*;
import com.docker.spring.app.models.Category;
import com.docker.spring.app.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DTOMapper {
    public ProductDTO productToProductDTO(Product product) {
        if (product == null) {
            return null;
        }
        return ProductDTO.builder()
                .productId(product.getProductId())
                .brand(product.getBrand())
                .name(product.getName())
                .inventory(product.getInventory())
                .description(product.getDescription())
                .partNumber(product.getPartNumber())
                .offerPrice(product.getOfferPrice())
                .listPrice(product.getListPrice())
                .category(CategoryDTO.builder()
                        .categoryId(product.getCategory().getCategoryId())
                        .description(product.getCategory().getDescription())
                        .identifier(product.getCategory().getIdentifier())
                        .markForDelete(product.getCategory().getMarkForDelete())
                        .published(product.getCategory().getPublished())
                        .name(product.getCategory().getName())
                        .build())
                .price(product.getPrice().stream().map(price -> PriceDto.builder()
                        .currency(price.getCurrency())
                        .priceType(price.getPriceType())
                        .updatedAt(price.getUpdatedAt())
                        .createdAt(price.getCreatedAt())
                        .price(price.getPrice())
                        .build()).collect(Collectors.toList()))
                .inventories(product.getInventories().stream().map(inventory -> InventoryDto.builder()
                        .expectedQuantity(inventory.getExpectedQuantity())
                        .quantity(inventory.getQuantity())
                        .updatedAt(inventory.getUpdatedAt())
                        .FMCenter(inventory.getFMCenter())
                        .createdAt(inventory.getCreatedAt())
                        .build()).collect(Collectors.toList()))
                .images(product.getImages().stream().map(image -> ImageDTO.builder()
                        .image(image.getImage())
                        .fileName(image.getFileName())
                        .downloadUrl(image.getDownloadUrl())
                        .fileType(image.getFileType())
                        .productId(image.getProduct().getProductId())
                        .build()).collect(Collectors.toList()))
                .build();
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

    public List<ProductDTO> productToProductDTOs(List<Product> product) {
        List<ProductDTO> productDTOS;
        productDTOS = product.stream().map(this::productToProductDTO).toList();
        return productDTOS;
    }
}
