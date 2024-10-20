package com.docker.spring.app.sevices;

import com.docker.spring.app.domains.AddProductRequest;
import com.docker.spring.app.domains.ProductDTO;

public interface ProductService {
    public ProductDTO addProduct(AddProductRequest addProductRequest);
    public ProductDTO getProductByProductId(String productId);
    public ProductDTO getProductByPartNumber(String productId);
    public void deleteProduct(String productId);
    public ProductDTO updateProduct(AddProductRequest addProductRequest);
}
