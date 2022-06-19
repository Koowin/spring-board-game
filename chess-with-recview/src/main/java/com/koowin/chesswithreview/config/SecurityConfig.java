package com.koowin.chesswithreview.config;

import com.koowin.chesswithreview.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
        return auth.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeRequests(a -> a
                        .mvcMatchers("/", "/login", "/register").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(l -> l
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true)
                )
                .logout(o -> o
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                )
                .httpBasic();

        return httpSecurity.build();
    }
}
