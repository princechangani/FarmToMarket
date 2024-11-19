package com.Farm2Market.FarmToMarket.repository;

import com.Farm2Market.FarmToMarket.entity.ProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {

}
