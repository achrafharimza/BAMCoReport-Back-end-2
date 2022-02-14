package com.bamcoreport.web.api.identity.services;

import com.bamcoreport.web.api.identity.config.SecurityConstants;
import com.bamcoreport.web.api.identity.controller.UserController;
import com.bamcoreport.web.api.identity.dto.services.IMapClassWithDto;
import com.bamcoreport.web.api.identity.entities.User;
import com.bamcoreport.web.api.identity.entities.UserContactInfo;
import com.bamcoreport.web.api.identity.helpers.HelpUpdate;
import com.bamcoreport.web.api.identity.repositories.RoleRepository;
import com.bamcoreport.web.api.identity.repositories.UserContactInfoRepository;
import com.bamcoreport.web.api.identity.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.bamcoreport.web.api.identity.dto.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService implements IUserService {

    static final org.apache.log4j.Logger log4j = org.apache.log4j.Logger.getLogger(UserService.class.getName());

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserContactInfoRepository userContactInfoRepository;

    @Autowired
    IMapClassWithDto<User, UserDto> userMapping;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    //----------------- get all users  : -----------------------------------------

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        return userMapping.convertListToListDto(users, UserDto.class);
    }

    //-------------------------------------------------------------------------------


    //----------------- Add user  : ---------------------------------------------------

    @Override
    public UserDto addUser(UserDto user) {
        String pass = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(pass);

        User userEntity = userMapping.convertToEntity(user, User.class);
        userEntity = userRepository.save(userEntity);
        if (userEntity != null) {

            UserContactInfo contactInfo = new UserContactInfo();
            contactInfo.setUserId(userEntity);
            contactInfo = userContactInfoRepository.save(contactInfo);

            userEntity.setUserContactInfo(contactInfo);
        }

        return userMapping.convertToDto(userEntity, UserDto.class);
    }

    //---------------------------------------------------------------------------------------


    //----------------- Get info user  : ---------------------------------------------------

    @Override
    public UserDto getById(long id) {
        User user = userRepository.getById(id);
        return userMapping.convertToDto(user, UserDto.class);
    }

    //------------------------------------------------------------------------------------------


    //----------------- delete user  : ---------------------------------------------------------

    @Override
    public boolean deleteUser(long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception ex) {
            log4j.error(ex.getMessage());
            return false;
        }
        return true;
    }

    //--------------------------------------------------------------------------------------------


    //--------- Update User : -------------------------------------------------------------------
    @Override
    public UserDto updateUser(UserDto user) {
        UserDto saved = userMapping.convertToDto(userRepository.getById(user.getId()), UserDto.class); // DB
        HelpUpdate.copyNonNullProperties(user, saved);
        User userEntity = userMapping.convertToEntity(saved, User.class);
        userEntity = userRepository.save(userEntity);
        return userMapping.convertToDto(userEntity, UserDto.class);
    }


    //--------------------------------------------------------------------------------------------


    //-------------- Authentication Security ------------------------------------------------------------------------------

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.findByUsername(username);

        if (userEntity == null) throw new UsernameNotFoundException(username);

        return new org.springframework.security.core.userdetails.User(userEntity.getUsername(), userEntity.getPassword(), new ArrayList<>());
    }

    //----------------------------------------------------------------------------------------------


    @Override
    public UserDto changepass(UserDto user) throws Exception {
        User userUpdated = null;

        String newpasswordcrypt = bCryptPasswordEncoder.encode(user.getNewpassword());
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser == null) {
            throw new Exception("user not found");
        }
        Boolean isPasswordCorrect = bCryptPasswordEncoder.matches(user.getPassword(), existingUser.getPassword());
        if (isPasswordCorrect) {
            existingUser.setPassword(newpasswordcrypt);
            userUpdated = userRepository.save(existingUser);

        }


        return userMapping.convertToDto(userUpdated, UserDto.class);


    }
   /////////////////////////////////////:::::::::::::::::: FILTERROLE
   public String filter ( String tokenb){
       System.out.println("\n");
       System.out.println("tokenb");
       System.out.println("\n");
       System.out.println(tokenb);

       String  token = tokenb.replace(SecurityConstants.TOKEN_PREFIX, "");

       System.out.println("\n");
       System.out.println("token");
       System.out.println(token);

       String username = Jwts.parser()
               .setSigningKey( SecurityConstants.TOKEN_SECRET )
               .parseClaimsJws( token )
               .getBody()
               .getSubject();

       System.out.println("\n");
       System.out.println("username");
       System.out.println(username);
       if (username != null) {
           System.out.println("\n");
           System.out.println("inif");
           System.out.println(username);
           User existingUser = userRepository.findByUsername(username);
           System.out.println("\n");
           System.out.println("userEntity");
           System.out.println(existingUser.getUsername());


           if (existingUser != null) {
               long id =existingUser.getId();

               String role=roleRepository.findrole(id);
               return role;

           }
       }


       return "not definded";







   }


}

