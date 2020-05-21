package com.codeByKochs.RestDemo.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration Bean used to allow Cross Origin Communictaion between client and server
 */

@Configuration
public class CORSConfiguration {
    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry){
                registry.addMapping("/**")
                        .allowedMethods("*")
                        .allowedOrigins("http://localhost:3000");
            }
        };
    }
}
