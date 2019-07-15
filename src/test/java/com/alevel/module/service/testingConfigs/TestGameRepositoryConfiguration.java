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
    @Primary  // in case of namesake beans, this one will be of highest priority
    public GameRepository gameRepository() {
        // TODO add return values here
        return Mockito.mock(GameRepository.class);
    }
}
