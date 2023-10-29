package com.gdsc2023.planyee.domain.tmap.config;


import com.gdsc2023.planyee.domain.tmap.service.TmapApiRoutesService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {

    @Bean
    public TmapApiRoutesService tmapApiRoutesService() {
        return new TmapApiRoutesService();
    }
}
