package com.softuni.service;

import com.softuni.base.TestBase;
import com.softuni.model.entity.User;
import com.softuni.model.service.UserServiceModel;
import com.softuni.repository.UserRepository;
import com.softuni.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class UserServiceImplTest extends TestBase {
    private List<User> usersList;

    @MockBean
    UserRepository userRepository;

    @Autowired
    UserService userService;
    @Autowired
    ModelMapper modelMapper;

    @BeforeEach
    public void setupTest() {
        usersList = new ArrayList<>();
        when(userRepository.findAll())
                .thenReturn(usersList);
    }

    @Test
    void findByUsername_shouldReturnUser_whenUserHasUsername() {
        String username = "Stiliyan";
        User userBefore = new User();
        userBefore.setUsername(username);

        when(userRepository.findByUsername(username)).thenReturn(java.util.Optional.of(userBefore));

        UserServiceModel userAfter = userService.findByUsername(username);

        assertEquals(userBefore.getUsername(), userAfter.getUsername());
    }

    @Test
    void getAll_whenUserIsValid_shouldPast(){
        User user = new User();
        user.setUsername("stst");
        user.setPassword("zlzl");

        List<User> users = new ArrayList<>(List.of(user));

      when(userRepository.findAll()).thenReturn(users);

        List<User> returnedUsers = userService.findAllUsers();

        assertEquals(users.size(), returnedUsers.size());
    }

    @Test
    public void findAllUsers_whenNoUsers_shouldReturnEmpty() {
        usersList.clear();
        List<User> allUsers = userService.findAllUsers();
        Assert.assertTrue(allUsers.isEmpty());
    }

    @Test
    public void findAllUsers_whenUsers_shouldReturnCorrect() {
        usersList.clear();

        User user = new User();

        usersList.add(user);

        List<User> allUsers = userService.findAllUsers();
        assertEquals(1, allUsers.size());
    }
    @Test
    public void loadByUsername_whenNoUser_shouldThrowEx() {
        usersList.clear();
        assertThrows(Exception.class,
                () -> userService.loadUserByUsername("name"));
    }

    @Test
    public void register_whenUsernameAlreadyExists_shouldThrowEx() {
        usersList.clear();

        User user = new User();
        user.setUsername("taken");

        usersList.add(user);
        UserServiceModel model = modelMapper.map(user,UserServiceModel.class);
        model.setUsername("taken");

        assertThrows(Exception.class,
                () -> userService.register(model));
    }
    @Test
    public void findByUsername_whenUsernameDoesntExist_shouldThrowEx() {
        usersList.clear();

        User user = new User();
        user.setUsername("name");

        usersList.add(user);
        assertThrows(Exception.class,
                () -> userService.findByUsername(""));
    }



}