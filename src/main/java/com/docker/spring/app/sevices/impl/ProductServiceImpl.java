package com.docker.spring.app.sevices.impl;

import com.docker.spring.app.dao.ProductRepository;
import com.docker.spring.app.domains.AddProductRequest;
import com.docker.spring.app.domains.ProductDTO;
import com.docker.spring.app.mappers.DTOMapper;
import com.docker.spring.app.models.Product;
import com.docker.spring.app.sevices.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    DTOMapper mapper;

    public ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Override
    public ProductDTO addProduct(AddProductRequest addProductRequest) {
        Product productToAdd = productRepository.saveAndFlush(Product.builder()
                        .partNumber(addProductRequest.getPartNumber())
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
    public ProductDTO updateProduct(AddProductRequest addProductRequest) {
        Product product = productRepository.findByPartNumber(addProductRequest.getPartNumber()).orElseThrow();
        Product productToAdd = productRepository.saveAndFlush(Product.builder()
                .partNumber(addProductRequest.getPartNumber())
                .build());
        return mapper.productToProductDTO(productToAdd);
    }
}
