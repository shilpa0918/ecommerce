package com.docker.spring.app.controllers;


import com.docker.spring.app.domains.AddProductRequest;
import com.docker.spring.app.domains.ProductDTO;
import com.docker.spring.app.domains.UpdateProductRequest;
import com.docker.spring.app.sevices.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product/api/v1")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping(path = "")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody AddProductRequest addProductRequest) {
        ProductDTO product = productService.addProduct(addProductRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping(path = "/productId/{productId}")
    public ResponseEntity<ProductDTO> getProductByProductId(@PathVariable Long productId) {
        ProductDTO product = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping(path = "/partNumber/{partNumber}")
    public ResponseEntity<ProductDTO> getProductByPartNumber(@PathVariable String partNumber) {
        ProductDTO product = productService.getProductByPartNumber(partNumber);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(product);
    }

    @PutMapping(path = "")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody UpdateProductRequest updateProductRequest) {
        ProductDTO product = productService.updateProduct(updateProductRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(product);
    }

    @DeleteMapping(path = "/{productId}")
    public ResponseEntity deleteProduct(@PathVariable String productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(path = "/category/{categoryName}")
    public ResponseEntity<List<ProductDTO>> getProductByCategoryName(@PathVariable String categoryName) {
        List<ProductDTO> productDTOS = productService.getProductByCategoryName(categoryName);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(productDTOS);
    }

    @GetMapping("/categoryAndBrand/{categoryName}/{brand}")
    public ResponseEntity<List<ProductDTO>> getProductByCategoryAndBrand(@PathVariable String categoryName,
                                                                         @PathVariable String brand) {
        List<ProductDTO> productDTOS = productService.getProductByCategoryAndBrand(categoryName, brand);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(productDTOS);
    }

    @GetMapping("/brandAndProductName/{brand}/{name}")
    public ResponseEntity<List<ProductDTO>> getProductByBrandAndName(@PathVariable String brand,
                                                                     @PathVariable String name){
        List<ProductDTO> productDTOS = productService.getProductByBrandAndName(brand,name);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(productDTOS);

    }
    @GetMapping("/countProductByBrandAndProductName/{brand}/{name}")
    public ResponseEntity<Long> countProductByBrandAndProductName(@PathVariable String brand,
                                                                     @PathVariable String name){
        Long productCount = productService.countProductByBrandAndProductName(brand,name);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(productCount);

    }
}
