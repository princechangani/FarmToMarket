package com.Farm2Market.FarmToMarket;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class UserConfiguration {


    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}


