package com.blueocn.ECommerceApplication.service.profile;

import com.blueocn.ECommerceApplication.model.entity.ProfileEntity;
import com.blueocn.ECommerceApplication.model.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }


    public ProfileEntity createProfile(Long userDetailsId, String firstName, String lastName, String email) {
        return profileRepository.save(new ProfileEntity(userDetailsId, firstName, lastName, email));
    }

    public List<ProfileEntity> getAllProfiles() {
        return profileRepository.findAll();
    }

    public ProfileEntity getProfileByUserId(Long userId) {
        return profileRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("User ID not found"));
    }

    public ProfileEntity updateProfileByUserId(Long userId, Long newUserId, String firstName, String lastName, String email) {
        return profileRepository.findByUserId(userId).map(currProfile -> {
            currProfile.setUserId(newUserId);
            currProfile.setFirstName(firstName);
            currProfile.setLastName(lastName);
            currProfile.setEmail(email);
            return profileRepository.save(currProfile);
        }).orElseThrow(() -> new RuntimeException("User ID not found"));
    }

    public void deleteProfileByUserId(Long userId) {
        if (profileRepository.existsByUserId(userId)) {
            profileRepository.deleteByUserId(userId);
        } else {
            throw new RuntimeException("User ID not found");
        }
    }

}
