package com.zello.zello.controller;

import com.zello.zello.domain.User;
import com.zello.zello.dto.CreateUserDTO;
import com.zello.zello.mapper.UserMapper;
import com.zello.zello.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public ResponseEntity<CreateUserDTO> resgiter(@RequestBody CreateUserDTO dto) {
        User user = userMapper.toUser(dto);
        CreateUserDTO response = userMapper.toCreateUserDTO(userService.newCreate(user));
        return ResponseEntity.ok(response);
    }
}
