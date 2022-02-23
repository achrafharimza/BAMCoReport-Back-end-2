package com.bamcoreport.web.api.identity.services;

import com.bamcoreport.web.api.identity.dto.model.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;


import java.util.List;

public interface IUserService extends UserDetailsService {

    List<UserDto> getUsers(int page, int limit);
    UserDto addUser(UserDto user) throws Exception;
    UserDto getById(long id) throws Exception;
    boolean deleteUser(long id);
    UserDto updateUser(UserDto user) throws Exception;
    UserDto changepass(UserDto userDto) ;

    String filter( String tokenb);



}
