package com.Farm2Market.FarmToMarket.controller;

import com.Farm2Market.FarmToMarket.dto.ProductDetailDto;
import com.Farm2Market.FarmToMarket.service.ProductDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product-detail")
public class ProductDetailController {

    private final ProductDetailService productDetailService;

    public ProductDetailController(ProductDetailService productDetailService) {
        this.productDetailService = productDetailService;
    }

    @GetMapping
    public List<ProductDetailDto> getAllProductDetails() {
        return productDetailService.getAllProductDetail();
    }

    @PostMapping("/{email}")
    public ResponseEntity<ProductDetailDto> saveProductDetail(@PathVariable String email, @RequestBody ProductDetailDto productDetailDto) {
        try {
            ProductDetailDto savedProductDetail = productDetailService.saveProductDetail(email, productDetailDto);
            return new ResponseEntity<>(savedProductDetail, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllProducts() {
        productDetailService.deleteAllProducts();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailDto> getProductDetailById(@PathVariable long id) {
        try {
            ProductDetailDto productDetail = productDetailService.getProductById(id);
            return new ResponseEntity<>(productDetail, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDetailDto> updateProduct(@PathVariable long id, @RequestBody ProductDetailDto updatedProductDetailDto) {
        try {
            ProductDetailDto updatedProduct = productDetailService.updateProduct(id, updatedProductDetailDto);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
