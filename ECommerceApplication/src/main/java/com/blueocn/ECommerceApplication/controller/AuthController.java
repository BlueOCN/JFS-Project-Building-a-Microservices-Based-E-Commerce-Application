package com.blueocn.ECommerceApplication.controller;

import com.blueocn.ECommerceApplication.model.dto.auth.AuthRegisterDTO;
import com.blueocn.ECommerceApplication.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@RequestBody AuthRegisterDTO authRegisterDTO) {
        Long userId = authService.registerUser(authRegisterDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .replacePath("/users/{id}")
                .buildAndExpand(userId)
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
