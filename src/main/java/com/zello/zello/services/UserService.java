package com.zello.zello.services;

import com.zello.zello.domain.User;
import com.zello.zello.exceptions.NotFoundException;
import com.zello.zello.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User newCreate(User user) {
        return userRepository.save(user);
    }

    public List<User> listAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }

    public void update(User user) {
        findById(user.getId());
        userRepository.save(user);
    }

    public void delete(Long id) {
        findById(id);
        userRepository.deleteById(id);
    }
}
