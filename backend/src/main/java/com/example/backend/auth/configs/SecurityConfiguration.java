package com.example.backend.auth.configs;

import com.example.backend.auth.repositories.RouteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final RouteRepository routeRepository;
    private final AuthenticationManager daoAuthenticationManager;
    private final CorsConfigurationSource corsConfigurationSource;
    private final SecurityBeans.DelegatedAuthenticationEntryPoint delegatedAuthenticationEntryPoint;
    private final SecurityBeans.DelegateAuthorizationEntryPoint delegateAuthorizationEntryPoint;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Value("${spring.excluded-urls}")
    private String [] excludedUrls;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(customizer -> customizer.configurationSource(corsConfigurationSource))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(this::authorizeHttpRequests)
                .authenticationManager(daoAuthenticationManager)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(customizer -> customizer
                        .authenticationEntryPoint(delegatedAuthenticationEntryPoint)
                        .accessDeniedHandler(delegateAuthorizationEntryPoint)
                )
                .build();
    }

    private void authorizeHttpRequests(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
        routeRepository.findAllByIsActiveTrue()
                .forEach(route -> {
                    if (Boolean.TRUE.equals(route.getIsPublic())) {
                        registry.requestMatchers(HttpMethod.valueOf(route.getHttpMethod().name()), route.getUri())
                                .permitAll();
                        log.info(String.format("Public route %s::%s added", route.getHttpMethod(), route.getUri()));
                    } else {
                        route.getPermissions().forEach(permission -> {
                            registry.requestMatchers(HttpMethod.valueOf(route.getHttpMethod().name()), route.getUri())
                                    .hasAuthority(permission.getName());
                            log.info(String.format("Permission for route %s", route.getUri()));
                        });
                    }
                });

        registry.requestMatchers(excludedUrls).permitAll();
        registry.anyRequest().authenticated();
    }


}
