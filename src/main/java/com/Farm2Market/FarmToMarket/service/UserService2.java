package com.Farm2Market.FarmToMarket.service;

import com.Farm2Market.FarmToMarket.dto.AddressDto; // Create an AddressDto class if you haven't already
import com.Farm2Market.FarmToMarket.dto.ProductDetailDto;
import com.Farm2Market.FarmToMarket.dto.UserDto;
import com.Farm2Market.FarmToMarket.entity.Address; // Import Address entity
import com.Farm2Market.FarmToMarket.entity.ProductDetail;
import com.Farm2Market.FarmToMarket.entity.UserEntity;
import com.Farm2Market.FarmToMarket.repository.AddressRepository; // Create an AddressRepository
import com.Farm2Market.FarmToMarket.repository.ProductDetailRepository;
import com.Farm2Market.FarmToMarket.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService2 {
    private final UserRepository userRepository;
    private final ProductDetailRepository productDetailRepository;
    private final AddressRepository addressRepository; // Add AddressRepository
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder encoder;

    public UserService2(UserRepository userRepository,
                       ProductDetailRepository productDetailRepository,
                       AddressRepository addressRepository, // Add AddressRepository to constructor
                       ModelMapper modelMapper,
                       BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.productDetailRepository = productDetailRepository;
        this.addressRepository = addressRepository; // Initialize AddressRepository
        this.modelMapper = modelMapper;
        this.encoder = encoder;
    }
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }

    public UserDto saveUser(UserDto userDto) {
        userDto.setPassword(encoder.encode(userDto.getPassword()));

        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userEntity.setProductDetails(new ArrayList<>());
        userEntity.setAddresses(new ArrayList<>()); // Initialize the addresses list

        UserEntity savedUser = userRepository.save(userEntity);

        saveProductDetails(userDto.getProductDetailsDto(), savedUser);
        saveAddresses(userDto.getAddressesDto(), savedUser); // Save addresses

        return mapToUserDto(savedUser);
    }

    @Transactional
    public UserDto findByUsername(String username) {
        Optional<UserEntity> optionalUser = userRepository.findByUsername(username);

        UserEntity user = optionalUser
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        user.getProductDetails().size();
        user.getAddresses().size(); // Ensure addresses are loaded

        return mapToUserDto(user);
    }

    @Transactional
    public void deleteByUsername(String username) {
        userRepository.deleteByUsername(username);
    }
    @Transactional
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    private void saveProductDetails(List<ProductDetailDto> productDetailDtos, UserEntity userEntity) {
        if (productDetailDtos != null && !productDetailDtos.isEmpty()) {
            productDetailDtos.forEach(productDto -> {
                ProductDetail productDetail = modelMapper.map(productDto, ProductDetail.class);
                productDetail.setUser(userEntity);
                productDetailRepository.save(productDetail);
            });
        }
    }

    private void saveAddresses(List<AddressDto> addressDtos, UserEntity userEntity) {
        if (addressDtos != null && !addressDtos.isEmpty()) {
            addressDtos.forEach(addressDto -> {
                Address address = modelMapper.map(addressDto, Address.class);
                address.setUser(userEntity);
                addressRepository.save(address);
            });
        }
    }

    private UserDto mapToUserDto(UserEntity userEntity) {
        UserDto userDto = modelMapper.map(userEntity, UserDto.class);

        if (userEntity.getProductDetails() != null) {
            List<ProductDetailDto> productDetailDtos = userEntity.getProductDetails().stream()
                    .map(productDetail -> modelMapper.map(productDetail, ProductDetailDto.class))
                    .collect(Collectors.toList());
            userDto.setProductDetailsDto(productDetailDtos);
        }

        // Map addresses
        if (userEntity.getAddresses() != null) { // Ensure you have a method to get addresses
            List<AddressDto> addressDtos = userEntity.getAddresses().stream()
                    .map(address -> modelMapper.map(address, AddressDto.class))
                    .collect(Collectors.toList());
            userDto.setAddressesDto(addressDtos);
        }

        return userDto;
    }

}
