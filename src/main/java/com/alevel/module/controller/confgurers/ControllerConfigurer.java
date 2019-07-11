package com.alevel.module.controller.confgurers;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfigurer {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
