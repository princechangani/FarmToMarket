package com.Farm2Market.FarmToMarket.repository;


import com.Farm2Market.FarmToMarket.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface AddressRepository extends JpaRepository<Address, Long> {
}
