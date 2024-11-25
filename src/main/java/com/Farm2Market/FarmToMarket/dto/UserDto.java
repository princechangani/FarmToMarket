package com.Farm2Market.FarmToMarket.dto;

import com.Farm2Market.FarmToMarket.entity.Address;
import com.Farm2Market.FarmToMarket.entity.ProductDetail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String username;
    private String email;
    private String password;

    private String firstName;
    private String middleName;
    private String lastName;

    private Long phoneNo;
    private String profile;
    private String bearerToken;
    private List<ProductDetailDto> productDetailsDto ;
    private List<AddressDto> addressesDto;
}

