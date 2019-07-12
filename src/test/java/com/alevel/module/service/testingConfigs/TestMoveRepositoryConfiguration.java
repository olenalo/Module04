package com.alevel.module.service.testingConfigs;

import com.alevel.module.service.repository.MoveRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class TestMoveRepositoryConfiguration {
    @Bean
    @Primary
    public MoveRepository moveRepository() {
        return Mockito.mock(MoveRepository.class);
    }
}
