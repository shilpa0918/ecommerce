package com.docker.spring.app.sevices.impl;

import com.docker.spring.app.dao.CategoryRepository;
import com.docker.spring.app.dao.ProductRepository;
import com.docker.spring.app.domains.AddProductRequest;
import com.docker.spring.app.domains.ProductDTO;
import com.docker.spring.app.domains.UpdateProductRequest;
import com.docker.spring.app.mappers.DTOMapper;
import com.docker.spring.app.models.Category;
import com.docker.spring.app.models.Image;
import com.docker.spring.app.models.Product;
import com.docker.spring.app.sevices.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

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
        Category category = categoryRepository.findById(addProductRequest.getCategoryId()).orElseThrow();
        Product productToAdd = productRepository.saveAndFlush(Product.builder()
                .partNumber(addProductRequest.getPartNumber())
                .brand(addProductRequest.getBrand())
                .name(addProductRequest.getName())
                .offerPrice(addProductRequest.getOfferPrice())
                .inventory(addProductRequest.getInventory())
                .listPrice(addProductRequest.getListPrice())
                .category(category)
                .images(addProductRequest.getImages()
                        .stream().map(imageDTO -> Image.builder().build()).collect(Collectors.toList()))
                .build());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBasicAuth("username", "password");
        ProductDTO productDTO = mapper.productToProductDTO(productToAdd);
        return productDTO;
    }

    @Override
    public ProductDTO getProductByProductId(String productId) {
        Product product = productRepository.findById(Long.valueOf(productId)).orElseThrow();
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
}
