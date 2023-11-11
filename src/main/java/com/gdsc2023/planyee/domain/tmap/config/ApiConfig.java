package com.gdsc2023.planyee.domain.tmap.config;


import com.gdsc2023.planyee.domain.tmap.service.routesApiService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class ApiConfig {


    public routesApiService tmapApiRoutesService() {
        return new routesApiService();
    }
}
