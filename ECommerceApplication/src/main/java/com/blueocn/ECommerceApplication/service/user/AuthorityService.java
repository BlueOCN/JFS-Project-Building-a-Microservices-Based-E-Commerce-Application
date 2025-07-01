package com.blueocn.ECommerceApplication.service.user;

import com.blueocn.ECommerceApplication.model.entity.AuthorityEntity;
import com.blueocn.ECommerceApplication.model.repository.AuthorityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    public AuthorityService(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public AuthorityEntity createAuthority(String username, String authority) {
        if (authorityRepository.existsByUsername(username)) {
            return authorityRepository.save(new AuthorityEntity(username, authority));
        } else {
            throw new IllegalArgumentException("Username is already taken.");
        }
    }

    public List<AuthorityEntity> getAllAuthorities(){
        return authorityRepository.findAll();
    }

    public AuthorityEntity getAuthorityByUsername(String username) {
        return authorityRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Username not found"));
    }

    public AuthorityEntity updateAuthorityByUsername(String username, String authority) {
        return authorityRepository.findByUsername(username).map(currAuthority -> {
            currAuthority.setUsername(username);
            currAuthority.setAuthority(authority);
            return authorityRepository.save(currAuthority);
        }).orElseThrow(() -> new RuntimeException("Username not found"));
    }

    public void deleteAuthorityByUsername(String username){
        if (authorityRepository.existsByUsername(username)) {
            authorityRepository.deleteByUsername(username);
        } else {
            throw new RuntimeException("Username not found");
        }
    }

}
