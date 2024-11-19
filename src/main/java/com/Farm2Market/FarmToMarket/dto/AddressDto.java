package com.Farm2Market.FarmToMarket.dto;

import lombok.Data;

@Data
public class AddressDto {
    private Long id;
    private int houseNo;
    private String street;
    private String landMark;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private Long userId;
// Country name

    // You can add additional fields as necessary, such as:
    // private String landmark;      // Optional landmark for easier navigation
    // private boolean isDefault;    // Flag to indicate if this is the default address
}