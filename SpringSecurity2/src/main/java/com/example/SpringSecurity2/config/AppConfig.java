package com.example.SpringSecurity2.config;

import com.example.SpringSecurity2.Service.SecurityResourceService;
import com.example.SpringSecurity2.repository.ResourcesRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public SecurityResourceService securityResourceService(ResourcesRepository resourcesRepository) {
        SecurityResourceService securityResourceService = new SecurityResourceService(resourcesRepository);
        return securityResourceService;
    }
}
