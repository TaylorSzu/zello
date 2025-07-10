package com.zello.zello.dto;

import java.time.LocalDate;

public record CreateUserDTO(
        String name,
        String email,
        String password,
        String type,
        String cpf,
        LocalDate dateBith,
        String profileImage
) {
}
