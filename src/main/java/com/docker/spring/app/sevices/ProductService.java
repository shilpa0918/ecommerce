package com.docker.spring.app.sevices;

import com.docker.spring.app.domains.AddProductRequest;
import com.docker.spring.app.domains.ProductDTO;
import com.docker.spring.app.domains.UpdateProductRequest;

import java.util.List;

public interface ProductService {
    ProductDTO addProduct(AddProductRequest addProductRequest);

    ProductDTO getProductById(Long productId);

    ProductDTO getProductByPartNumber(String partNumber);

    void deleteProduct(String productId);

    ProductDTO updateProduct(UpdateProductRequest addProductRequest);

    List<ProductDTO> getProductByCategoryName(String categoryName);

    List<ProductDTO> getProductByCategoryAndBrand(String categoryName, String brand);

    List<ProductDTO> getProductByBrandAndName(String brand, String name);

    Long countProductByBrandAndProductName(String brand, String name);
}
