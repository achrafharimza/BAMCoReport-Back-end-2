package com.bamcoreport.web.api.identity.services;

import com.bamcoreport.web.api.identity.dto.model.UserDto;
import com.bamcoreport.web.api.identity.dto.model.UserMembershipDto;
import com.bamcoreport.web.api.identity.dto.services.IMapClassWithDto;
import com.bamcoreport.web.api.identity.entities.User;
import com.bamcoreport.web.api.identity.entities.UserMembership;
import com.bamcoreport.web.api.identity.helpers.HelpUpdate;
import com.bamcoreport.web.api.identity.repositories.UserMembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserMembershipService implements IUserMembershipService {
    static  final org.apache.log4j.Logger log4j = org.apache.log4j.Logger.getLogger(UserMembershipService.class.getName());


    @Autowired
    UserMembershipRepository userMembershipRepository;
    @Autowired
    IMapClassWithDto<UserMembership, UserMembershipDto> userMembershipMapping;

    //----------------- get all getUserMemberships  : -----------------------------------------

    @Override
    public List<UserMembershipDto> getUserMemberships() {
        List<UserMembership> UserMemberships = userMembershipRepository.findAll();
        return userMembershipMapping.convertListToListDto(UserMemberships,UserMembershipDto.class);
    }

    //-------------------------------------------------------------------------------
    //----------------- Add UserMembership : ---------------------------------------------------

    @Override
    public UserMembershipDto addUserMembership(UserMembershipDto userMembershipdto) {
        UserMembership userMembershipEntity=userMembershipMapping.convertToEntity(userMembershipdto,UserMembership.class);
        userMembershipEntity=userMembershipRepository.save(userMembershipEntity);
        return userMembershipMapping.convertToDto(userMembershipEntity,UserMembershipDto.class);
    }

    //---------------------------------------------------------------------------------------
    //----------------- Get info UserMembership  : ---------------------------------------------------

    @Override
    public UserMembershipDto getById(long id) {
        UserMembership userEntity = userMembershipRepository.getById(id);
        return userMembershipMapping.convertToDto(userEntity,UserMembershipDto.class);
    }

    //------------------------------------------------------------------------------------------
    //----------------- delete UserMembership  : ---------------------------------------------------------

    @Override
    public boolean deleteUserMembership(long id) {
        try {
            userMembershipRepository.deleteById(id);
        }catch (Exception ex){
            log4j.error(ex.getMessage());
            return false;
        }
        return true;
    }

    //--------------------------------------------------------------------------------------------
    //--------- Update UserMembership : -------------------------------------------------------------------
    @Override
    public UserMembershipDto updateuserMembership(UserMembershipDto userMembership) {
        UserMembershipDto saved = userMembershipMapping.convertToDto(userMembershipRepository.getById(userMembership.getId()),UserMembershipDto.class); // DB
        HelpUpdate.copyNonNullProperties(userMembership,saved);
        UserMembership userMembershipEntity=userMembershipMapping.convertToEntity(saved,UserMembership.class);
        userMembershipEntity=userMembershipRepository.save(userMembershipEntity);
        return userMembershipMapping.convertToDto(userMembershipEntity,UserMembershipDto.class);
    }

    //--------------------------------------------------------------------------------------------






}
