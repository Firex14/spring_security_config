package com.yaba.spring_security.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
@EnableWebMvc
public class securityConfig implements WebMvcConfigurer {

    private final RequestFilter requestFilter;
    private final CustomAuthenticationManager authenticationManager;
    private final CustomRouteMatchers routeMatchers;


    @Value("${allowed.methods}")
    private String[] allowedMethods;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf((csrf)-> csrf.disable())
                .httpBasic(hb-> hb.disable())
                .authorizeHttpRequests(req -> req.requestMatchers(routeMatchers)
                        .permitAll().anyRequest().authenticated()
                ).authenticationManager(authenticationManager)
                .addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(e -> e.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins()
                .exposedHeaders("*")
                .allowedHeaders("*")
                .allowedMethods(allowedMethods);
    }
}
