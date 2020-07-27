package com.softuni.service.impl;

import com.softuni.service.AuthenticatedUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component

public class AuthenticatedUserServiceImpl implements AuthenticatedUserService {

    @Override
    public String getUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName(); 
    }

}
