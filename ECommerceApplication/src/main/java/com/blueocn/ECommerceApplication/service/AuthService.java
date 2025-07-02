package com.blueocn.ECommerceApplication.service;

import com.blueocn.ECommerceApplication.model.dto.auth.AuthLoginDTO;
import com.blueocn.ECommerceApplication.model.dto.auth.AuthRegisterDTO;
import com.blueocn.ECommerceApplication.model.dto.auth.AuthResponseDTO;
import com.blueocn.ECommerceApplication.model.dto.auth.UnauthorizedException;
import com.blueocn.ECommerceApplication.model.entity.UserEntity;
import com.blueocn.ECommerceApplication.model.entity.ProfileEntity;
import com.blueocn.ECommerceApplication.service.user.AuthorityService;
import com.blueocn.ECommerceApplication.service.user.UserService;
import com.blueocn.ECommerceApplication.service.user.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;
    private final ProfileService profileService;
    private final AuthorityService authorityService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    public AuthService(UserService userService, ProfileService profileService, AuthorityService authorityService) {
        this.userService = userService;
        this.profileService = profileService;
        this.authorityService = authorityService;
    }

    public Long registerUser(AuthRegisterDTO authRegisterDTO) {
        UserEntity userDetails = userService.createUser(authRegisterDTO.getUsername(), authRegisterDTO.getPassword(),true);
        ProfileEntity user = profileService.createProfile(userDetails.getUserId(), authRegisterDTO.getFirstName(), authRegisterDTO.getLastName(), authRegisterDTO.getEmail());
        authorityService.createAuthority(authRegisterDTO.getUsername(), authRegisterDTO.getRole());
        return user.getProfileId();
    }

    public AuthResponseDTO loginUser(AuthLoginDTO body) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(body.getUsername(), body.getPassword())
            );

            if (authentication.isAuthenticated()) {
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                return new AuthResponseDTO("Bearer", jwtService.generateToken(userDetails), jwtService.getExpiration());
            }
        } catch (BadCredentialsException e) {
            throw new UnauthorizedException("Invalid username or password");
        }

        return new AuthResponseDTO();
    }
}
