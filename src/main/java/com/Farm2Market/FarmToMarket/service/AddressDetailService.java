package com.Farm2Market.FarmToMarket.service;

import com.Farm2Market.FarmToMarket.dto.AddressDto;
import com.Farm2Market.FarmToMarket.dto.AddressDto;
import com.Farm2Market.FarmToMarket.entity.Address;
import com.Farm2Market.FarmToMarket.entity.UserEntity;
import com.Farm2Market.FarmToMarket.repository.AddressRepository;
import com.Farm2Market.FarmToMarket.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressDetailService {

    private final AddressRepository addressDetailRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public AddressDetailService(AddressRepository addressDetailRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.addressDetailRepository = addressDetailRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    // Get all address details
    public List<AddressDto> getAllAddressDetails() {
        return addressDetailRepository.findAll().stream()
                .map(addressDetail -> modelMapper.map(addressDetail, AddressDto.class))
                .toList();
    }

    // Save address detail for a user
    public AddressDto saveAddressDetail(String username, AddressDto addressDto) {
        Optional<UserEntity> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();

            Address addressDetail = modelMapper.map(addressDto, Address.class);
            addressDetail.setUser(user); // Assuming AddressDetail has a UserEntity reference

            Address savedAddress = addressDetailRepository.save(addressDetail);
            return modelMapper.map(savedAddress, AddressDto.class);
        } else {
            throw new RuntimeException("User not found for username: " + username);
        }
    }

    // Delete all address details
    public void deleteAllAddresses() {
        addressDetailRepository.deleteAll();
    }

    // Get address detail by ID
    public AddressDto getAddressById(long id) {
        Address addressDetail = addressDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found for id: " + id));
        return modelMapper.map(addressDetail, AddressDto.class);
    }

    // Update address detail by ID
    public AddressDto updateAddress(long id, AddressDto updatedAddressDto) {
        Address addressDetail = addressDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found for id: " + id));

        // Update fields
        addressDetail.setHouseNo(updatedAddressDto.getHouseNo());
        addressDetail.setStreet(updatedAddressDto.getStreet());
        addressDetail.setLandMark(updatedAddressDto.getLandMark());
        addressDetail.setCity(updatedAddressDto.getCity());
        addressDetail.setState(updatedAddressDto.getState());
        addressDetail.setPostalCode(updatedAddressDto.getPostalCode());
        addressDetail.setCountry(updatedAddressDto.getCountry());

        Address updatedAddress = addressDetailRepository.save(addressDetail);
        return modelMapper.map(updatedAddress, AddressDto.class);
    }
}
