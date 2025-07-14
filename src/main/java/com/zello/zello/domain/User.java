package com.zello.zello.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Builder
@Getter
@Setter
@With
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true, updatable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String type;
    @Column(nullable = false, unique = true)
    private String cpf;
    @Column(nullable = false)
    private LocalDate dateBith;
    @Column(nullable = false)
    private String profileImage;
    @Column(nullable = true)
    private String status;
    @Column(nullable = true)
    private Boolean isEmailValidated;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //vamos resgatar as roles para fazermos a regra das routes
        return List.of();
    }

    @Override
    public String getUsername() { //vamos pegar o username que sera nosso emmail para verificar se esse usuario pertence ao que esta tentando logar
        return email;
    }

    @Override
    public String getPassword() { //mesma coisa para a senha
        return password;
    }
}
