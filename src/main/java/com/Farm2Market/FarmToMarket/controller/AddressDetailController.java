package com.Farm2Market.FarmToMarket.controller;

import com.Farm2Market.FarmToMarket.dto.AddressDto;
import com.Farm2Market.FarmToMarket.service.AddressDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address-detail")
public class AddressDetailController {

    private final AddressDetailService addressDetailService;

    public AddressDetailController(AddressDetailService addressDetailService) {
        this.addressDetailService = addressDetailService;
    }

    // Get all address details
    @GetMapping
    public List<AddressDto> getAllAddressDetails() {
        return addressDetailService.getAllAddressDetails();
    }

    // Save address detail for a user
    @PostMapping("/{username}")
    public ResponseEntity<AddressDto> saveAddressDetail(@PathVariable String username, @RequestBody AddressDto addressDetailDto) {
        try {
            AddressDto savedAddressDetail = addressDetailService.saveAddressDetail(username, addressDetailDto);
            return new ResponseEntity<>(savedAddressDetail, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Delete all address details
    @DeleteMapping
    public ResponseEntity<Void> deleteAllAddresses() {
        addressDetailService.deleteAllAddresses();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get address detail by ID
    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> getAddressDetailById(@PathVariable long id) {
        try {
            AddressDto addressDetail = addressDetailService.getAddressById(id);
            return new ResponseEntity<>(addressDetail, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Update address detail by ID
    @PutMapping("/{id}")
    public ResponseEntity<AddressDto> updateAddress(@PathVariable long id, @RequestBody AddressDto updatedAddressDetailDto) {
        try {
            AddressDto updatedAddress = addressDetailService.updateAddress(id, updatedAddressDetailDto);
            return new ResponseEntity<>(updatedAddress, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
