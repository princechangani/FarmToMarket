package com.Farm2Market.FarmToMarket.service;

import com.Farm2Market.FarmToMarket.dto.ProductDetailDto;
import com.Farm2Market.FarmToMarket.entity.ProductDetail;
import com.Farm2Market.FarmToMarket.entity.UserEntity;
import com.Farm2Market.FarmToMarket.repository.ProductDetailRepository;
import com.Farm2Market.FarmToMarket.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ProductDetailService {

    private final ProductDetailRepository productDetailRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public ProductDetailService(ProductDetailRepository productDetailRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.productDetailRepository = productDetailRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    public List<ProductDetailDto> getAllProductDetail() {
        return productDetailRepository.findAll().stream()
                .map(productDetail -> modelMapper.map(productDetail, ProductDetailDto.class))
                .toList();
    }


    public ProductDetailDto saveProductDetail(String email, ProductDetailDto productDetailDto) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();

            ProductDetail productDetail = modelMapper.map(productDetailDto, ProductDetail.class);
            productDetail.setUser(user);

            ProductDetail savedProduct = productDetailRepository.save(productDetail);
            return modelMapper.map(savedProduct, ProductDetailDto.class);
        } else {
            throw new RuntimeException("User not found for username: " + email);
        }
    }


    public void deleteAllProducts() {
        productDetailRepository.deleteAll();
    }


    public ProductDetailDto getProductById(long id) {
        ProductDetail productDetail = productDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found for id: " + id));
        return modelMapper.map(productDetail, ProductDetailDto.class);
    }


    public ProductDetailDto updateProduct(long id, ProductDetailDto updatedProductDetailDto) {
        ProductDetail productDetail = productDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found for id: " + id));

        productDetail.setProductCategory(updatedProductDetailDto.getProductCategory());
        productDetail.setProductName(updatedProductDetailDto.getProductName());
        productDetail.setProductDescription(updatedProductDetailDto.getProductDescription());
        productDetail.setProductPrice(updatedProductDetailDto.getProductPrice());
        productDetail.setDeliveryOption(updatedProductDetailDto.getDeliveryOption());
        productDetail.setProductUnit(updatedProductDetailDto.getProductUnit());
        productDetail.setDiscount(updatedProductDetailDto.getDiscount());
        productDetail.setLocation(updatedProductDetailDto.getLocation());
        productDetail.setPaymentMethod(updatedProductDetailDto.getPaymentMethod());

        ProductDetail updatedProduct = productDetailRepository.save(productDetail);
        return modelMapper.map(updatedProduct, ProductDetailDto.class);
    }
}

