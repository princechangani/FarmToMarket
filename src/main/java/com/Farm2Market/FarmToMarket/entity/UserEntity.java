package com.Farm2Market.FarmToMarket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Indexed;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username", "email"})})
@Entity

public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private Long phoneNo;
    private String profile;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProductDetail> productDetails = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Address> addresses = new ArrayList<>();

    public void addProductDetail(ProductDetail productDetail) {
        productDetails.add(productDetail);
        productDetail.setUser(this);
    }

    public void removeProductDetail(ProductDetail productDetail) {
        productDetails.remove(productDetail);
        productDetail.setUser(null);
    }

    public void addAddress(Address address) {
        addresses.add(address);
        address.setUser(this);
    }

    public void removeAddress(Address address) {
        addresses.remove(address);
        address.setUser(null);
    }

}