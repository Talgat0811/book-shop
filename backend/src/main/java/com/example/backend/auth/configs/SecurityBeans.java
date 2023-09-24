package com.example.backend.auth.configs;

import com.example.backend.auth.services.impl.DaoUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
@RequiredArgsConstructor
public class SecurityBeans {
    private final DaoUserDetailsService daoUserService;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager daoAuthenticationManager() {
        var provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(daoUserService);

        return new ProviderManager(provider);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Component
    public static class DelegatedAuthenticationEntryPoint implements AuthenticationEntryPoint {
        @Qualifier("handlerExceptionResolver")
        private final HandlerExceptionResolver handlerExceptionResolver;
        public DelegatedAuthenticationEntryPoint(HandlerExceptionResolver handlerExceptionResolver) {
            this.handlerExceptionResolver = handlerExceptionResolver;
        }

        @Override
        public void commence(HttpServletRequest request,
                             HttpServletResponse response,
                             AuthenticationException authException) {
            handlerExceptionResolver.resolveException(request, response, null, authException);
        }
    }

    @Component
    public static class DelegateAuthorizationEntryPoint implements AccessDeniedHandler {
        @Qualifier("handlerExceptionResolver")
        private final HandlerExceptionResolver handlerExceptionResolver;

        public DelegateAuthorizationEntryPoint(HandlerExceptionResolver handlerExceptionResolver) {
            this.handlerExceptionResolver = handlerExceptionResolver;
        }

        @Override
        public void handle(HttpServletRequest request,
                           HttpServletResponse response,
                           AccessDeniedException accessDeniedException) {
            handlerExceptionResolver.resolveException(request, response, null, accessDeniedException);
        }
    }

}
