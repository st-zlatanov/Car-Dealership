package com.softuni.service;

import com.softuni.model.entity.Role;
import com.softuni.model.service.RoleServiceModel;

import java.util.Set;

public interface RoleService {
    RoleServiceModel findByAuthority(String auth);
    void seedRolesInDb();
    Set<RoleServiceModel> findAllRoles();
}
