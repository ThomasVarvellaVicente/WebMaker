package com.webmaker.api.config;

import com.webmaker.api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserRepository userRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF if you're building a REST API (or not using forms):
                .csrf(AbstractHttpConfigurer::disable)

                // Define which endpoints are public vs. require auth:
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/register").permitAll() // registration is public
                        // (Add "/auth/login" here as well if you want that to be public)
                        .anyRequest().authenticated() // everything else requires a logged-in user
                )

                // Use HTTP Basic (for Postman or simple Basic Auth):
                .httpBasic(Customizer.withDefaults());

        // Return the fully built security configuration
        return http.build();
    }

    /**
     * This tells Spring Security how to load user details from your database,
     * instead of creating a random in-memory user with a generated password.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByCredentialsUsername(username)
                .map(entity -> {
                    // Use the DB's hashed password and a default role "USER"
                    // (If your User entity has a boolean isAdmin, you can set roles accordingly)
                    return User
                            .withUsername(entity.getCredentials().getUsername())
                            .password(entity.getCredentials().getPassword()) // already bcrypt-hashed
                            .roles("USER") // or "ADMIN" if isAdmin = true, etc.
                            .build();
                })
                .orElseThrow(() -> new UsernameNotFoundException("No user with username: " + username));
    }

    /**
     * Use BCrypt for hashing/storing passwords in the DB.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}