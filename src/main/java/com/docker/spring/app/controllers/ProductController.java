package com.docker.spring.app.controllers;


import com.docker.spring.app.domains.AddProductRequest;
import com.docker.spring.app.domains.ProductDTO;
import com.docker.spring.app.domains.UpdateProductRequest;
import com.docker.spring.app.sevices.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product/api/v1")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping(path = "")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody AddProductRequest addProductRequest){
        ProductDTO product = productService.addProduct(addProductRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping(path = "/{productId}")
    public ResponseEntity<ProductDTO> getProductByProductId(@PathVariable String productId){
        ProductDTO product = productService.getProductByProductId(productId);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }
    @GetMapping(path = "/{partNumber}")
    public ResponseEntity<ProductDTO> getProductByPartNumber(@PathVariable String productId){
        ProductDTO product = productService.getProductByPartNumber(productId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(product);
    }

    @PutMapping(path = "")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody UpdateProductRequest updateProductRequest){
        ProductDTO product = productService.updateProduct(updateProductRequest);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(product);
    }

    @DeleteMapping(path = "/{productId}")
    public ResponseEntity deleteProduct(@PathVariable String productId){
        productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
