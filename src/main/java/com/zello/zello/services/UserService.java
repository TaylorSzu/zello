package com.zello.zello.services;

import com.zello.zello.domain.User;
import com.zello.zello.exceptions.NotFoundException;
import com.zello.zello.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
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
        User savedUser = findById(user.getId());
        User userToSave = user.withType(savedUser.getType());
        if (userToSave.getPassword() == null) {
            userToSave = userToSave.withPassword(savedUser.getPassword());
        }
        userRepository.save(userToSave);
    }

    public void delete(Long id) {
        findById(id);
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new NotFoundException("User not found"));
    }
}
