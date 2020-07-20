package com.softuni.service.impl;

import com.softuni.model.entity.Role;
import com.softuni.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.softuni.model.entity.User;
import com.softuni.model.service.UserServiceModel;
import com.softuni.repository.UserRepository;
import com.softuni.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserServiceModel register(UserServiceModel userServiceModel) {
        userServiceModel.setRole(this.roleService
                .findByName(this.userRepository.count()==0
                        ? "ADMIN" : "USER"));
        User user = this.modelMapper
                .map(userServiceModel, User.class);

        return this.modelMapper
                .map(this.userRepository.saveAndFlush(user),
                        UserServiceModel.class);
    }

    @Override
    public UserServiceModel findByUsername(String username) {
        return this.userRepository.findByUsername(username)
                .map(user -> this.modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public List<String> findAllUsernames() {
        return this.userRepository.findAll().stream().map(User::getUsername).collect(Collectors.toList());
    }

    @Override
    public void addRoleToUser(String username, String role) {
        User user = this.userRepository.findByUsername(username).orElse(null);
        if(!user.getRole().getName().equals(role)){
            Role roleEntity = this.modelMapper.map(this.roleService.findByName(role), Role.class);
            user.setRole(roleEntity);
            this.userRepository.saveAndFlush(user);
        }
    }
}
