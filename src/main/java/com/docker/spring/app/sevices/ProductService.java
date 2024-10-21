package com.docker.spring.app.sevices;

import com.docker.spring.app.domains.AddProductRequest;
import com.docker.spring.app.domains.ProductDTO;
import com.docker.spring.app.domains.UpdateProductRequest;

public interface ProductService {
    ProductDTO addProduct(AddProductRequest addProductRequest);
    ProductDTO getProductByProductId(String productId);
    ProductDTO getProductByPartNumber(String productId);
    void deleteProduct(String productId);
    ProductDTO updateProduct(UpdateProductRequest addProductRequest);
}
