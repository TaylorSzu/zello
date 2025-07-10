package com.zello.zello.mapper;

import com.zello.zello.domain.User;
import com.zello.zello.dto.CreateUserDTO;

public interface UserMapper {
    User toUser(CreateUserDTO dto);
    CreateUserDTO toCreateUserDTO(User user);
}
