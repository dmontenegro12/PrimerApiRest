package com.api.user.security;

import com.api.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Autowired
    public UserService userService;

//manejar por roles
    /*@Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((auth) -> auth
                .anyRequest().authenticated()
        );
        httpSecurity.formLogin(Customizer.withDefaults());
        httpSecurity.httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }*/
    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity)throws Exception {
        httpSecurity.csrf().disable().authorizeHttpRequests((auth)-> auth
                .requestMatchers("/user/**").permitAll()
                .requestMatchers("/admin/**").hasRole("Admin")
                .anyRequest().authenticated());

        httpSecurity.formLogin(Customizer.withDefaults());
        httpSecurity.httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
