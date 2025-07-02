package com.blueocn.ECommerceApplication.model.dto.auth;


import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public class AuthLoginDTO {

    @NotBlank(message = "Username is required")
    @Length(min = 2, message = "Username must over 2 characters")
    @Length(max = 255, message = "Username must under 255 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Length(min = 4, message = "Password must have more than 4 characters")
    @Length(max = 64, message = "Password must be under 64 characters")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
