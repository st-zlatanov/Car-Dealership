package com.softuni.service;


import com.softuni.model.service.UserServiceModel;

import java.util.List;

public interface UserService {
    UserServiceModel register(UserServiceModel userServiceModel);

    UserServiceModel findByUsername(String username);

    List<String> findAllUsernames();

    void addRoleToUser(String username, String role);
}
