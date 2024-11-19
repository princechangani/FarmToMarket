package com.Farm2Market.FarmToMarket.service;

import com.Farm2Market.FarmToMarket.entity.UserEntity;
import com.Farm2Market.FarmToMarket.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepo;

    public UserDetailServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Find the user by email
        UserEntity user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Here I'm assuming that the `UserEntity` class has a field that stores the role of the user
        // If there's a roles list, you can replace "USER" with a dynamic role fetch.
        // Default role if not set

        // Build and return UserDetails
        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}