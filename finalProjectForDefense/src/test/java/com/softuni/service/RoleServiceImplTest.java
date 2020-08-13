package com.softuni.service;

import com.softuni.base.TestBase;
import com.softuni.model.binding.RoleAddBindingModel;
import com.softuni.model.binding.UserRegisterBindingModel;
import com.softuni.model.entity.Role;
import com.softuni.model.service.RoleServiceModel;
import com.softuni.repository.RoleRepository;
import com.softuni.service.impl.RoleServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class RoleServiceImplTest extends TestBase {
    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private static final String ROLE_USER = "ROLE_USER";

    @MockBean
    RoleRepository roleRepository;

    @Autowired
    RoleService roleService;

    @Test
    public void findAllRoles_whenRolesExist_shouldReturnCorrect() {
        Role role1 = new Role(ROLE_ADMIN);
        Role role2 = new Role(ROLE_USER);

        List<Role> roles = new ArrayList<>();
        roles.add(role1);
        roles.add(role2);

        Mockito.when(roleRepository.findAll()).thenReturn(roles);
        Set<RoleServiceModel> result = roleService.findAllRoles();

        Assert.assertEquals(roles.size(), result.size());
    }

    @Test
    public void findByAuthority_whenValidInput_shouldReturnCorrect() {
        String authority = "admin";
        Role role = new Role();
        role.setAuthority("ADMIN");
        Mockito.when(roleRepository.findByAuthority(authority)).thenReturn(role);
        RoleServiceModel result = roleService.findByAuthority(authority);

        Assert.assertEquals(role.getAuthority(), result.getAuthority());
    }
    @Test
    void settingFieldsOfAddBindingModel_shouldWorkCorrectly(){
        RoleAddBindingModel model = new RoleAddBindingModel();
        model.setRole("ADMIN");
        model.setUsername("stilkata");

        Assertions.assertEquals("stilkata", model.getUsername());
        Assertions.assertEquals("ADMIN", model.getRole());

    }
}