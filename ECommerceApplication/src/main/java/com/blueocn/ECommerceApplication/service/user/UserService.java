package com.blueocn.ECommerceApplication.service.user;

import com.blueocn.ECommerceApplication.model.entity.UserEntity;
import com.blueocn.ECommerceApplication.model.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity createUser(String username, String password, boolean enabled) {
        UserEntity userDetails = new UserEntity(username, passwordEncoder.encode(password), enabled);
        return userRepository.save(userDetails);
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity getUsersByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Username not found"));
    }

    public UserEntity updateUsersByUsername(String username, String newUsername, String newPassword, boolean newEnabled) {
        return userRepository.findByUsername(username).map(currUserDetails -> {
           currUserDetails.setUsername(newUsername);
           currUserDetails.setPassword(passwordEncoder.encode(newPassword));
           currUserDetails.setEnabled(newEnabled);
           return userRepository.save(currUserDetails);
        }).orElseThrow(() -> new RuntimeException("Username not found"));
    }

    public void deleteUsersByUsername(String username) {
        if (userRepository.existsByUsername(username)) {
            userRepository.deleteByUsername(username);
        } else {
            throw new RuntimeException("Username not found");
        }
    }

}
