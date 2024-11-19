package com.Farm2Market.FarmToMarket.controller;

import com.Farm2Market.FarmToMarket.dto.UserDto;
import com.Farm2Market.FarmToMarket.service.UserService3;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService3 userService;



    public AdminController(UserService3 userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

}
