package com.blueocn.ECommerceApplication.service;

import com.blueocn.ECommerceApplication.model.dto.auth.AuthRegisterDTO;
import com.blueocn.ECommerceApplication.model.entity.UserEntity;
import com.blueocn.ECommerceApplication.model.entity.ProfileEntity;
import com.blueocn.ECommerceApplication.service.user.AuthorityService;
import com.blueocn.ECommerceApplication.service.user.UserService;
import com.blueocn.ECommerceApplication.service.user.ProfileService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserService userService;
    private final ProfileService profileService;
    private final AuthorityService authorityService;

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
}
