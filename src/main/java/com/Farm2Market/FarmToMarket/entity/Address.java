package com.Farm2Market.FarmToMarket.entity;

import com.Farm2Market.FarmToMarket.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int houseNo;
    private String street;
    private  String landMark;
    private String city;
    private String state;
    private String postalCode;
    private String country;

    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)

    private UserEntity user;

}
