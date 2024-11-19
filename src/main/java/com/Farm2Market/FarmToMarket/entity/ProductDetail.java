package com.Farm2Market.FarmToMarket.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "product_details")
public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    private UserEntity user;


}