package com.blueocn.ECommerceApplication.model.dto.auth;

public class AuthResponseDTO {

    private String type;
    private String token;
    private Long expiresIn;

    public AuthResponseDTO() {
    }

    public AuthResponseDTO(String type, String token, Long expiresIn) {
        this.type = type;
        this.token = token;
        this.expiresIn = expiresIn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
