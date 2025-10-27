package br.rafael.eurekaserver.infra.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class EurekaSecurityConfig {
    
    private final static String realm = "fintech-realm"; 

    @Bean
    SecurityFilterChain securityFilterConfig(HttpSecurity http) throws Exception {
        http
            .csrf((csrf) -> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
            .httpBasic(basic -> basic.realmName(realm));

        return http.build();
    }

}
