package com.alevel.module.service.testingConfigs;

import com.alevel.module.service.MoveService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

// @Profile("test")
// @Configuration
public class TestMoveServiceConfiguration {

    // @Bean
    @Primary
    public MoveService moveService() {
        return Mockito.mock(MoveService.class);
    }
}
