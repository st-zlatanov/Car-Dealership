package com.softuni.service;


import com.softuni.model.entity.Offer;
import com.softuni.model.entity.User;
import com.softuni.model.service.RoleServiceModel;
import com.softuni.model.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserServiceModel register(UserServiceModel userServiceModel);

    UserServiceModel findByUsername(String username);

    List<String> findAllUsernames();

    void addRoleToUser(String username, String role);

    List<RoleServiceModel> findAuthorities(String username);

    List<User> findAllUsers();

    List<Offer> getAllOffersOfUser(String id);
}
