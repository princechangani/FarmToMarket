package com.Farm2Market.FarmToMarket.controller;

import com.Farm2Market.FarmToMarket.dto.UserDto;
import com.Farm2Market.FarmToMarket.entity.UserEntity;
import com.Farm2Market.FarmToMarket.service.UserService2;
import com.Farm2Market.FarmToMarket.service.UserService3;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/user2")
public class UserController2 {
    private final UserService3 userService;

    public UserController2(UserService3 userService) {
        this.userService = userService;

    }



    @PostMapping
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) {
        UserDto savedUser = userService.saveUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
    @PostMapping("/verify")
    public String verifyUser(@RequestBody UserDto userDto) {
        return userService.verify(userDto);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteByUsername(@PathVariable String username) {
        try {
            userService.deleteByUsername(username);
            return ResponseEntity.noContent().build();
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllUsers() {
        userService.deleteAllUsers();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDto> findByUsername(@PathVariable String email) {
        try {
            UserDto user = userService.findByEmail(email);
            return ResponseEntity.ok(user);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}