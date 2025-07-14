package com.zello.zello.controller;

import com.zello.zello.domain.User;
import com.zello.zello.dto.CreateUserDTO;
import com.zello.zello.dto.UpdateUserDTO;
import com.zello.zello.dto.UserDTO;
import com.zello.zello.mapper.UserMapper;
import com.zello.zello.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<UserDTO>> listAll() {
        List<UserDTO> response = userMapper.toListUserDTO(userService.listAll());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findUser(@PathVariable Long id) {
        UserDTO response = userMapper.toUserDTO(userService.findById(id));
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody UpdateUserDTO dto) {
        userService.update(userMapper.toUser(dto));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
