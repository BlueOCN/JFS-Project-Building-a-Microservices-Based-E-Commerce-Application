package com.blueocn.ECommerceApplication.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users") // for JdbcUserDetailsManager compatibility
public class UserDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long userId;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @OneToOne(mappedBy = "userDetails")
    private UserEntity userEntity;
}

