package com.softuni.service.impl;

import com.softuni.model.service.RoleServiceModel;
import com.softuni.repository.RoleRepository;
import com.softuni.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.softuni.model.entity.User;
import com.softuni.model.service.UserServiceModel;
import com.softuni.repository.UserRepository;
import com.softuni.service.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService, RoleRepository roleRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

//    @Override
//    public UserServiceModel register(UserServiceModel userServiceModel) {
//        userServiceModel.setAuthorities(this.roleService
//                .findByName(this.userRepository.count()==0
//                        ? "ADMIN" : "USER"));
//        User user = this.modelMapper
//                .map(userServiceModel, User.class);
//
//        return this.modelMapper
//                .map(this.userRepository.saveAndFlush(user),
//                        UserServiceModel.class);
//    }

    @Override
    public UserServiceModel register(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel, User.class);

        if (this.userRepository.count() == 0) {
            user.setAuthorities(new HashSet<>(this.roleRepository.findAll()));
        } else {
            user.setAuthorities(new HashSet<>(Set.of(this.roleRepository.findByAuthority("USER"))));
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return this.modelMapper.map(this.userRepository.saveAndFlush(user),
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
    public void addRoleToUser(String username, String auth) {
        User user = this.userRepository.findByUsername(username).orElse(null);
        UserServiceModel userServiceModel = this.modelMapper.map(user, UserServiceModel.class);

        boolean hasAuthority = false;

        for (RoleServiceModel authority : userServiceModel.getAuthorities()) {
            if (authority.getAuthority().equals("ADMIN")) {
                hasAuthority = true;
            }
        }

        if (hasAuthority) {
            userServiceModel.getAuthorities().clear();
            userServiceModel.getAuthorities().add(this.roleService.findByAuthority("USER"));
        } else {
            userServiceModel.getAuthorities().add(this.roleService.findByAuthority("ADMIN"));
        }

        this.userRepository.saveAndFlush(this.modelMapper.map(userServiceModel, User.class));
    }

    @Override
    public List<RoleServiceModel> findAuthorities(String username) {
        User user = this.userRepository.findByUsername(username).orElse(null);
        UserServiceModel userServiceModel = this.modelMapper.map(user, UserServiceModel.class);
        return userServiceModel.getAuthorities().stream().collect(Collectors.toList());
    }

    @Override
    public List<User> findAllUsers() {
        return new ArrayList<>(this.userRepository.findAll());
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return this.userRepository.findByUsername(s).orElse(null);
    }
}
