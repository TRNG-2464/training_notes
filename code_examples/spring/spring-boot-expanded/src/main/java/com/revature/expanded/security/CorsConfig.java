package com.revature.expanded.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfig = new CorsConfiguration();

        /*
         * Here, we explicitly name the trusted origins. Here, these
         * endpoints works for:
         *  Standard Angular Frontend Endpoint: http://localhost:4200
         *  Standard React Frontend Endpoint: http://localhost:3000
         *  Standard React Vite Dev Endpoint: http://localhost:5173
         *
         * Note: You could use a wildcard here, but you should never
         * be this lax. Make sure that you only list the exact, trusted
         * endpoints.
         */
        corsConfig.setAllowedOrigins( List.of("http://localhost:4200",
                "http://localhost:3000", "http://localhost:5173") );

        // Allow Get, Post, Put and Delete methods - the standard operations
        // for our simple App
        corsConfig.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));

        // Allow all headers
        corsConfig.setAllowedHeaders(List.of("*"));

        /*
         * The following is required for cookies to be included in the
         * origin servers requests
         */
        corsConfig.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return source;
    }
}
