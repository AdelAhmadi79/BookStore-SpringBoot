package com.example.book_store.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthorizationFilterConfig {

    @Bean
    public AuthorizationFilter authorizationFilter() {
        return new AuthorizationFilter();
    }
}
