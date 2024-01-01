package com.ugurukku.linkshortener.config;

import com.ugurukku.linkshortener.model.property.SecurityProperty;
import com.ugurukku.linkshortener.security.AuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final SecurityProperty property;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
                                                         PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   AuthorizationFilter authorizationFilter) throws Exception {
        return http
                .authorizeHttpRequests(request -> {
                    // Swagger UI
                    request.requestMatchers(property.getAllowedUrls()).permitAll();
                    request.requestMatchers("/api/v1/admin/**").hasAuthority("ADMIN");
                    request.anyRequest().authenticated();
                })
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
//                .exceptionHandling(eh -> eh.authenticationEntryPoint(authEntryPoint))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

//    @Component
//    @RequiredArgsConstructor
//    @Slf4j
//    public static class AuthEntryPoint implements AuthenticationEntryPoint {
//
//        @Qualifier("handlerExceptionResolver")
//        private final HandlerExceptionResolver resolver;
//
//        @Override
//        public void commence(HttpServletRequest request,
//                             HttpServletResponse response,
//                             AuthenticationException authException) {
//
//            authException.printStackTrace();
//            resolver.resolveException(request, response, null,new RuntimeException("FORBIDDEN"));
//        }
//    }
}
