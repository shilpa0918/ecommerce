package com.docker.spring.app.sevices.impl;

import com.docker.spring.app.dao.CategoryRepository;
import com.docker.spring.app.dao.ProductRepository;
import com.docker.spring.app.domains.AddProductRequest;
import com.docker.spring.app.domains.ProductDTO;
import com.docker.spring.app.domains.UpdateProductRequest;
import com.docker.spring.app.mappers.DTOMapper;
import com.docker.spring.app.models.*;
import com.docker.spring.app.sevices.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    static final String CLASS_NAME = ProductServiceImpl.class.getName();
    final Logger logger = Logger.getLogger(CLASS_NAME);

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    DTOMapper mapper;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductDTO addProduct(AddProductRequest addProductRequest) {
        String methodName = "addProduct";
        logger.entering(CLASS_NAME, methodName);
        Category category = categoryRepository.findById(addProductRequest.getCategoryId()).orElseThrow();
        Product product = Product.builder()
                .partNumber(addProductRequest.getPartNumber())
                .brand(addProductRequest.getBrand())
                .name(addProductRequest.getName())
                .offerPrice(addProductRequest.getOfferPrice())
                .inventory(addProductRequest.getInventory())
                .listPrice(addProductRequest.getListPrice())
                .category(category)
                .description(addProductRequest.getDescription())
                .build();
        List<Price> prices = addProductRequest.getPriceDtos().stream().map(priceDto -> {
            return Price.builder()
                    .currency(priceDto.getCurrency())
                    .priceType(priceDto.getPriceType())
                    .updatedAt(priceDto.getUpdatedAt())
                    .createdAt(priceDto.getCreatedAt())
                    .price(priceDto.getPrice())
                    .product(product)
                    .build();
        }).collect(Collectors.toList());
        product.setPrice(prices);
        List<Inventory> inventories = addProductRequest.getInventoryDtos().stream().map(inventoryDto -> {
            return Inventory.builder()
                    .expectedQuantity(inventoryDto.getExpectedQuantity())
                    .quantity(inventoryDto.getQuantity())
                    .updatedAt(inventoryDto.getUpdatedAt())
                    .FMCenter(inventoryDto.getFMCenter())
                    .createdAt(inventoryDto.getCreatedAt())
                    .product(product)
                    .build();
        }).collect(Collectors.toList());
        product.setInventories(inventories);
        List<Image> images = addProductRequest.getImages()
                .stream().map(image -> {
                    return Image.builder()
                            .image(image.getImage())
                            .fileName(image.getFileName())
                            .downloadUrl(image.getDownloadUrl())
                            .fileType(image.getFileType())
                            .product(product)
                            .build();
                }).collect(Collectors.toList());
        product.setImages(images);
        Product productAdded = productRepository.saveAndFlush(product);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBasicAuth("username", "password");
        ProductDTO productDTO = mapper.productToProductDTO(productAdded);
        return productDTO;
    }

    @Override
    public ProductDTO getProductById(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        ProductDTO productDTO = mapper.productToProductDTO(product);
        return productDTO;
    }

    @Override
    public ProductDTO getProductByPartNumber(String partNumber) {
        Product product = productRepository.findByPartNumber(partNumber).orElseThrow();
        return mapper.productToProductDTO(product);
    }

    @Override
    public void deleteProduct(String productId) {
        Product product = productRepository.findById(Long.valueOf(productId)).orElseThrow();
        productRepository.deleteById(product.getProductId());
    }

    @Override
    public ProductDTO updateProduct(UpdateProductRequest updateProductRequest) {
        Product product = productRepository.findById(updateProductRequest.getProductId()).orElseThrow();
        Category category = categoryRepository.findById(updateProductRequest.getCategoryId()).orElseThrow();
        product.setName(updateProductRequest.getName());
        product.setOfferPrice(updateProductRequest.getOfferPrice());
        product.setBrand(updateProductRequest.getBrand());
        product.setDescription(updateProductRequest.getDescription());
        product.setListPrice(updateProductRequest.getListPrice());
        product.setInventory(updateProductRequest.getInventory());
        product.setCategory(category);
        product.setPartNumber(updateProductRequest.getPartNumber());
        product.setImages(updateProductRequest.getImages()
                .stream().map(imageDTO -> Image.builder().build()).collect(Collectors.toList()));
        Product productToUpdate = productRepository.saveAndFlush(product);
        return mapper.productToProductDTO(productToUpdate);
    }

    @Override
    public List<ProductDTO> getProductByCategoryName(String categoryName) {
        List<Product> products = productRepository.findByCategoryName(categoryName);
        return mapper.productToProductDTOs(products);
    }

    @Override
    public List<ProductDTO> getProductByCategoryAndBrand(String categoryName, String brand) {
        List<Product> products = productRepository.findByCategoryNameAndBrand(categoryName, brand);
        return mapper.productToProductDTOs(products);
    }

    @Override
    public List<ProductDTO> getProductByBrandAndName(String brand, String name) {
        List<Product> products = productRepository.findByBrandAndName(brand,name);
        return mapper.productToProductDTOs(products);
    }

    @Override
    public Long countProductByBrandAndProductName(String brand, String name) {
        Long productCount = productRepository.countByBrandAndName(brand,name);
        return productCount;
    }
}
