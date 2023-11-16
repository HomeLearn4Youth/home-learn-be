package com.homelearn.back.common.config;

import com.homelearn.back.common.filter.JwtFilter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Getter
    private static final String[] PERMITTED_URL={
//            "/**"
            "/user/add",
            "/user/findemail",
            "/user/findpass",
            "/user/login",
            "/user/refresh",
            "/notice/find/**",
            "/notice/findlist/**",
            "/review/findlist/**",
            "/dong/**"
    };
    private final JwtFilter jwtFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf().disable()
                .formLogin().disable()
                .authorizeHttpRequests(
                        auth->auth
                             .antMatchers(PERMITTED_URL).permitAll()
                             .anyRequest().authenticated()
                                .and()
                                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                );
    return httpSecurity.build();
    }
}
