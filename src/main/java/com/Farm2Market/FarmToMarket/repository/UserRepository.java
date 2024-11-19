package com.Farm2Market.FarmToMarket.repository;

import com.Farm2Market.FarmToMarket.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);
    Optional<UserEntity> findByEmail(String email);// For Optional return type

    void deleteByUsername(String username);


}