package com.softuni.model.view;

import com.softuni.model.entity.Role;

import java.util.Set;

public class ProfileViewModel {
    private String username;
    private String email;
    private Set<Role> authorities;

    public ProfileViewModel() {
    }

    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }
}

