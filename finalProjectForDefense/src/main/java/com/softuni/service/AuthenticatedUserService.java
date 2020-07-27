package com.softuni.service;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface  AuthenticatedUserService {
    String getUsername();
}
