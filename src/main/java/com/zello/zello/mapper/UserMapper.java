package com.zello.zello.mapper;

import com.zello.zello.domain.User;
import com.zello.zello.dto.CreateUserDTO;
import com.zello.zello.dto.UpdateUserDTO;
import com.zello.zello.dto.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(CreateUserDTO dto);
    User toUser(UpdateUserDTO dto);
    CreateUserDTO toCreateUserDTO(User user);
    UserDTO toUserDTO(User user);
    List<UserDTO> toListUserDTO(List<User> users);
}
