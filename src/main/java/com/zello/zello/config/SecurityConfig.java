package com.zello.zello.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { //função para criar um filtro de segurança
        return http.csrf(AbstractHttpConfigurer::disable) //disabilita o csrf
                .authorizeHttpRequests(auth -> auth //aqui eu falo quem pode acessar
                        .requestMatchers(HttpMethod.POST, "/users").permitAll() //aqui fala que qualquer um pode acessar o essa rota sem ta logada
                        .requestMatchers(HttpMethod.GET, "/users/").hasRole("ADMIN") // /users/* -> /users/1 se for com ** ele protege tudo depois dessa patern
                        .anyRequest().authenticated() //aqui fala que qualquer outra requisição precisa de autenticação
                )
                .httpBasic(Customizer.withDefaults())
                .build(); //finaliza
    }

    @Bean
    public UserDetailsService userDetailsService() { //função para carregar os dados do usuario
        UserDetails user = User.withUsername("Taylor").password("{noop}12345").roles("USER").build();
        UserDetails admin = User.withUsername("Sherek").password("{noop}gostoso").roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
