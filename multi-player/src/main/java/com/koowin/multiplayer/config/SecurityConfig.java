package com.koowin.multiplayer.config;

import com.koowin.multiplayer.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final MemberService memberService;
  private PasswordEncoder passwordEncoder;

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
    return auth.getAuthenticationManager();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.
        csrf(c -> c
            .ignoringAntMatchers("/h2-console/**")
        )
        .headers(h -> h
            .frameOptions().sameOrigin()
        )
        .authorizeRequests(a -> a
            .antMatchers("/login", "/register", "/h2-console/**", "/games/**").permitAll()
            .anyRequest().authenticated()
        )
        .formLogin(l -> l
            .loginPage("/login")
            .defaultSuccessUrl("/", true)
        )
        .logout(o -> o
            .logoutUrl("/logout")
            .logoutSuccessUrl("/login")
        )
        .httpBasic();

    return http.build();
  }
}
