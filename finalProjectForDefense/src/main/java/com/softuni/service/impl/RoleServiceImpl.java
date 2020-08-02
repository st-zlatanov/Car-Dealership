package com.softuni.service.impl;

import com.softuni.model.entity.Role;
import com.softuni.model.service.RoleServiceModel;
import com.softuni.repository.RoleRepository;
import com.softuni.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RoleServiceModel findByAuthority(String auth) {
        return this.modelMapper.map(this.roleRepository.findByAuthority(auth),RoleServiceModel.class);
    }

    @Override
    public void seedRolesInDb() {
        if(roleRepository.count()==0){
            this.roleRepository.saveAndFlush(new Role("ADMIN"));
            this.roleRepository.saveAndFlush(new Role("USER"));
        }
    }



}
