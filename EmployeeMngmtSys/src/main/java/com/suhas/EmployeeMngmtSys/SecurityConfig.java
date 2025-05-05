package com.suhas.EmployeeMngmtSys;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .formLogin()
            .and()
            .httpBasic();
        

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var userDetailsManager = new InMemoryUserDetailsManager();

        userDetailsManager.createUser(User.withUsername("admin")
            .password("{noop}password")  // {noop} is a no-op password encoder for plain text
            .roles("ADMIN")
            .build());

        userDetailsManager.createUser(User.withUsername("user")
            .password("{noop}password")
            .roles("USER")
            .build());

        return userDetailsManager;
    }
}
