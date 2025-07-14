package com.zello.zello.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final static String[] WHITE_LIST = {"/swagger-ui.html", "/v3/**", "/swagger-ui/**"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { //função para criar um filtro de segurança
        return http.csrf(AbstractHttpConfigurer::disable) //disabilita o csrf
                .authorizeHttpRequests(auth -> auth //aqui eu falo quem pode acessar
                        .requestMatchers(WHITE_LIST).permitAll()
                        .requestMatchers(HttpMethod.POST, "/users").permitAll() //aqui fala que qualquer um pode acessar o essa rota sem ta logada
                        .requestMatchers(HttpMethod.GET, "/users/").hasRole("ADMIN") // /users/* -> /users/1 se for com ** ele protege tudo depois dessa patern
                        .anyRequest().authenticated() //aqui fala que qualquer outra requisição precisa de autenticação
                )
                .httpBasic(Customizer.withDefaults())
                .build(); //finaliza
    }

    @Bean
    public PasswordEncoder encode() { // vamos criar uma função que vai criptografar a senha
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /*@Bean  criamos alguns usario em memoria que pode aurenticar
    public UserDetailsService userDetailsService() { //função para carregar os dados do usuario
        UserDetails user = User.withUsername("Taylor").password("{noop}12345").roles("USER").build();
        UserDetails admin = User.withUsername("Sherek").password("{noop}gostoso").roles("ADMIN").build();
        return new InMemoryUserDetailsManager(user, admin);
    }*/

}
