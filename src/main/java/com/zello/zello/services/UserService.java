package com.zello.zello.services;

import com.zello.zello.domain.User;
import com.zello.zello.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User newCreate(User user) {
        return userRepository.save(user);
    }
}
