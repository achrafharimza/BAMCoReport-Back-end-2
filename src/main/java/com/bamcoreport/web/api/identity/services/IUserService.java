package com.bamcoreport.web.api.identity.services;

import com.bamcoreport.web.api.identity.dto.model.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;


import java.util.List;

public interface IUserService extends UserDetailsService {

    List<UserDto> getUsers();
    UserDto addUser(UserDto user);
    UserDto getById(long id);
    boolean deleteUser(long id);
    UserDto updateUser(UserDto user);
    UserDto changepass(UserDto user) throws Exception;

    String filter( String tokenb);




}
