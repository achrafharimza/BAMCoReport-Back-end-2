package com.bamcoreport.web.api.identity.services;

import com.bamcoreport.web.api.identity.dto.model.UserContactInfoDto;
import com.bamcoreport.web.api.identity.dto.model.UserDto;
import com.bamcoreport.web.api.identity.dto.services.IMapClassWithDto;
import com.bamcoreport.web.api.identity.entities.User;
import com.bamcoreport.web.api.identity.entities.UserContactInfo;
import com.bamcoreport.web.api.identity.helpers.HelpUpdate;
import com.bamcoreport.web.api.identity.repositories.UserContactInfoRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class UserContactInfoService implements IUserContactInfoService{

    static  final org.apache.log4j.Logger log4j = org.apache.log4j.Logger.getLogger(UserContactInfoService.class.getName());

    @Autowired
    UserContactInfoRepository userContactInfoRepository;

    @Autowired
    IMapClassWithDto<UserContactInfo, UserContactInfoDto> userMapping;

    //----------------- get infos users  by id : --------------------------------------------

    @Override
    public List<UserContactInfoDto> getUsersContactInfo() {
        List<UserContactInfo> userContactInfos = userContactInfoRepository.findAll();
        return userMapping.convertListToListDto(userContactInfos,UserContactInfoDto.class);
    }

    //-------------------------------------------------------------------------------------------



    // ----------------- add user info : -------------------------------------------------------
    @Override
    public UserContactInfoDto addUserinfo(UserContactInfoDto userinfo) {
        UserContactInfo usercontact = userMapping.convertToEntity(userinfo,UserContactInfo.class);
        usercontact=userContactInfoRepository.save(usercontact);
        return userMapping.convertToDto(usercontact,UserContactInfoDto.class);
    }

    //---------------------------------------------------------------------------------------------



    // ----------------- delete info user : ----------------------------------------------------

    @Override
    public boolean deleteUserinfo(long id) {
        try {
            userContactInfoRepository.deleteById(id);
        }catch (Exception ex){
            log4j.error(ex.getMessage());
            return false;
        }
        return true;
    }

    //----------------------------------------------------------------------------------------------


    // ----------------- Update Info d'un user  : ----------------------------------------------------


    @Override
    public UserContactInfoDto updateInfoUser(UserContactInfoDto userInfo) {
        UserContactInfoDto saved = userMapping.convertToDto(userContactInfoRepository.getById(userInfo.getId()),UserContactInfoDto.class); // DB
        HelpUpdate.copyNonNullProperties(userInfo,saved);
        UserContactInfo userEntity=userMapping.convertToEntity(saved,UserContactInfo.class);
        userEntity=userContactInfoRepository.save(userEntity);
        return userMapping.convertToDto(userEntity, UserContactInfoDto.class);
    }

    //----------------------------------------------------------------------------------------------





}
