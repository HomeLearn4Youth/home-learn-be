package com.homelearn.back.common.config;

import com.homelearn.back.common.filter.JwtFilter;
import com.homelearn.back.oauth.CustomOauthService;
import com.homelearn.back.oauth.handler.FailureHander;
import com.homelearn.back.oauth.handler.SuccessHandler;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Getter
    private static final String[] PERMITTED_URL={
//            "/**",
            "/user/add",
            "/user/findemail",
            "/user/findpass",
            "/user/login",
            "/user/refresh",
            "/notice/find/**",
            "/notice/findlist/**",
            "/review/findlist/**",
            "/dong/**",
            "/oauth/**",
            "/user/oauth/**",
            "/login/**",
    };
    private final JwtFilter jwtFilter;
    private final CustomOauthService customOauthService;
    private final SuccessHandler successHandler;
    private final FailureHander failureHander;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity
                .authorizeHttpRequests(
                        auth->auth
                             .antMatchers(PERMITTED_URL).permitAll()
                             .anyRequest().authenticated()
                );
        httpSecurity
                .oauth2Login()
                .successHandler(successHandler)
                .failureHandler(failureHander)
                    .userInfoEndpoint()
                    .userService(customOauthService);
        httpSecurity
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    return httpSecurity.build();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
