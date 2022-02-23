package com.bamcoreport.web.api.identity.services;

import com.bamcoreport.web.api.identity.config.SecurityConstants;
import com.bamcoreport.web.api.identity.dto.services.IMapClassWithDto;
import com.bamcoreport.web.api.identity.entities.User;
import com.bamcoreport.web.api.identity.entities.UserContactInfo;
import com.bamcoreport.web.api.identity.exceptions.ErrorMessages;
import com.bamcoreport.web.api.identity.helpers.HelpUpdate;
import com.bamcoreport.web.api.identity.repositories.RoleRepository;
import com.bamcoreport.web.api.identity.repositories.UserContactInfoRepository;
import com.bamcoreport.web.api.identity.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.bamcoreport.web.api.identity.dto.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService implements IUserService{

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserContactInfoRepository userContactInfoRepository;

    @Autowired
    IMapClassWithDto<User, UserDto> userMapping;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    RoleRepository roleRepository;

    //----------------- get all users  : -----------------------------------------
    @Override
    public List<UserDto> getUsers(int page, int limit) {
        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<User> users = userRepository.findAll(pageableRequest);
        return userMapping.convertPageToListDto(users,UserDto.class);

    }



//    @Override
//    public List<UserDto> getUsers() {
//
//        List<User> users = userRepository.findAll();
//        return userMapping.convertListToListDto(users,UserDto.class);
//    }

    //-------------------------------------------------------------------------------
    @Override
    public UserDto changepass(UserDto userDto) {
        User userUpdated=null;
        String newpasswordCrypte =bCryptPasswordEncoder.encode(userDto.getNewpassword());
        User existingUser =userRepository.findByUsername(userDto.getUsername());
        Boolean isPasswordMatches = bCryptPasswordEncoder.matches(userDto.getPassword(),existingUser.getPassword());

        if(isPasswordMatches){
            System.out.println("matches");
            existingUser.setPassword(newpasswordCrypte);
            userUpdated = userRepository.save(existingUser);
        }

        return userMapping.convertToDto(userUpdated,UserDto.class);
    }




    //----------------- Add user  : ---------------------------------------------------


    @Override
    public UserDto addUser(UserDto user) throws Exception {
//        if(existingUser == null) throw new Exception("deja existe");
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
//        User userEntity=userMapping.convertToEntity(user,User.class);
//        userEntity=userRepository.save(userEntity);
//        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
//        UserContactInfo contactInfo=new UserContactInfo();
//        contactInfo.setUserId(userEntity);
//        contactInfo=userContactInfoRepository.save(contactInfo);
//
//        userEntity.setUserContactInfo(contactInfo);
//
//        return userMapping.convertToDto(userEntity,UserDto.class);
    }

   //---------------------------------------------------------------------------------------



    //----------------- Get info user  : ---------------------------------------------------

    @Override
    public UserDto getById(long id) throws Exception {
        User user = userRepository.findById(id).orElse(null);
        if(user==null) throw new Exception("ErrorMessages.NO_RECORD_FOUND.getErrorMessage()");

        return userMapping.convertToDto(user,UserDto.class);
    }

    //------------------------------------------------------------------------------------------



    //----------------- delete user  : ---------------------------------------------------------

    @Override
    public boolean deleteUser(long id) {
        try {
            userRepository.deleteById(id);
        }catch (Exception ex){
             new Exception(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
            return false;
        }
        return true;
    }

    //--------------------------------------------------------------------------------------------


    //--------- Update User : -------------------------------------------------------------------
    @Override
    public UserDto updateUser(UserDto user) throws Exception {
        User existingUser =userRepository.findById(user.getId()).orElse(null);
        if(existingUser == null) throw new Exception(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        UserDto saved = userMapping.convertToDto(existingUser,UserDto.class); // DB
//        UserDto saved = userMapping.convertToDto(userRepository.findById(user.getId()),UserDto.class); // DB
        HelpUpdate.copyNonNullProperties(user,saved);
        User userEntity=userMapping.convertToEntity(saved,User.class);
        userEntity=userRepository.save(userEntity);
        return userMapping.convertToDto(userEntity,UserDto.class);
    }


    //--------------------------------------------------------------------------------------------




    //-------------- Authentication Security ------------------------------------------------------------------------------

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.findByUsername(username);


        if(userEntity == null) throw new UsernameNotFoundException(username);

        return new org.springframework.security.core.userdetails.User(userEntity.getUsername(), userEntity.getPassword(), new ArrayList<>());
    }

    //----------------------------------------------------------------------------------------------
//////////////////////////filter rol

    @Override
    public String filter(String tokenb) {
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
            User userEntity = userRepository.findByUsername(username);

            System.out.println("\n");
            System.out.println("userEntity");
            System.out.println(userEntity.getUsername());


            if (userEntity != null) {
                long id =userEntity.getId();

                String role=roleRepository.findrole(id);
                return role;

            }
        }


        return "not definded";






    }



}

