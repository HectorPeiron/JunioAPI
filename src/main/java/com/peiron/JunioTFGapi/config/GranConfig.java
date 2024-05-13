package com.peiron.JunioTFGapi.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GranConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}


