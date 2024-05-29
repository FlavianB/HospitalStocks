package com.example.hospitalstocks.Configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity(debug=true)
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF protection
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/login*", "/signup", "/css/**", "/js/**").permitAll() // Allow access to login, sign-up pages and static resources
                        .anyRequest().hasAnyRole("ADMIN", "USER") // All other requests must be authenticated
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/perform_login")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/perform_logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll()
                );
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return (request, response, exception) -> {
            // Custom logic for handling authentication failure
            response.sendRedirect("/login.html?error=true");
        };
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return (request, response, authentication) -> {
            // Custom logic for handling logout success
            response.sendRedirect("/login.html?logout=true");
        };
    }
}

