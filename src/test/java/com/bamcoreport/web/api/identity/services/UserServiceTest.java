package com.bamcoreport.web.api.identity.services;

import com.bamcoreport.web.api.identity.dto.model.UserDto;
import com.bamcoreport.web.api.identity.dto.services.IMapClassWithDto;
import com.bamcoreport.web.api.identity.entities.User;
import com.bamcoreport.web.api.identity.entities.UserContactInfo;
import com.bamcoreport.web.api.identity.repositories.UserContactInfoRepository;
import com.bamcoreport.web.api.identity.repositories.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    UserContactInfoRepository userContactInfoRepository;

    @Mock
    IMapClassWithDto<User, UserDto> userMapping;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getUsers() {
        List<UserDto> list=new ArrayList<>();
        UserDto u=new UserDto();
        u.setId(1L);
        u.setEnabled(true);
        u.setUsername("test1");
        u.setPassword("password");
        list.add(u);

        UserDto u2=new UserDto();
        u2.setId(2L);
        u2.setEnabled(true);
        u2.setUsername("test2");
        u2.setPassword("pass");
        u2.setFirstname("testeeeeeee");
        u2.setLastname("loubnaaaaaaaaaaa");
        list.add(u2);

        when(userService.getUsers()).thenReturn(list);
        List<UserDto> ud=userService.getUsers();
        System.out.println(ud);

    }

    @Test
    void addUser() {
        UserDto u = new UserDto();
        u.setId(2L);
        u.setUsername("loubna");
        u.setLastname("SOUSSI");
        u.setPassword("hahahaha");


        when(userService.addUser(u)).thenReturn(u);
        UserDto ud=userService.addUser(u);

    }

    @Test
    void getById() {
        UserDto u=new UserDto();
        u.setId(1L);
        u.setUsername("test");
        when(userService.getById(anyLong())).thenReturn(u);
        System.out.println(userService.getById(1L));
    }

    @Test
    void deleteUser() {
    }

    @Test
    void updateUser() {
        UserDto u = new UserDto();
        u.setId(2L);
        u.setUsername("loubna");
        u.setLastname("SOUSSI");
        //System.out.println(bCryptPasswordEncoder.encode("hahahaha"));
        u.setPassword("hahahaha");

        User uu=userMapping.convertToEntity(u,User.class);

        when(userRepository.getById(anyLong())).thenReturn(uu);
        when(userMapping.convertToDto(uu,UserDto.class)).thenReturn(u);
        when(userService.updateUser(u)).thenReturn(u);
        UserDto ud=userService.updateUser(u);
        System.out.println(ud);


    }


}