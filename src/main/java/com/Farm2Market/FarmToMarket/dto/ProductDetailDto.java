package com.Farm2Market.FarmToMarket.dto;

import com.Farm2Market.FarmToMarket.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailDto {

    private Long id;

    private String productName;
    private String productCategory;
    private String productDescription;
    private int productPrice;
    private int productQuantity;
    private String productUnit;
    private String deliveryOption;
    private String paymentMethod;
    private String discount;
    private String location;

    private Long userId;
}