package com.zello.zello.mapper;

import com.zello.zello.annotation.EncodedMapping;
import com.zello.zello.domain.User;
import com.zello.zello.dto.CreateUserDTO;
import com.zello.zello.dto.UpdateUserDTO;
import com.zello.zello.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = PasswordEncoderMapper.class, //precisa dizer ao MapStruct q ele pode os metodos dessa classe
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {
    @Mapping(target = "password", qualifiedBy = EncodedMapping.class) //vai pegar a classe q tem essa anotação e vai usar na hora da conversão
    User toUser(CreateUserDTO dto);
    @Mapping(target = "password", qualifiedBy = EncodedMapping.class)
    User toUser(UpdateUserDTO dto);
    CreateUserDTO toCreateUserDTO(User user);
    UserDTO toUserDTO(User user);
    List<UserDTO> toListUserDTO(List<User> users);
}
