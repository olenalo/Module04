package com.alevel.module.service.testingConfigs;

import com.alevel.module.service.repository.GameRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class TestGameRepositoryConfiguration {
    @Bean
    @Primary
    public GameRepository gameRepository() {
        return Mockito.mock(GameRepository.class);
    }
}
