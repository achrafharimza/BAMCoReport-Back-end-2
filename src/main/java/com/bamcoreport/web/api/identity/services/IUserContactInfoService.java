package com.bamcoreport.web.api.identity.services;

import com.bamcoreport.web.api.identity.dto.model.UserContactInfoDto;
import com.bamcoreport.web.api.identity.dto.model.UserDto;
import com.bamcoreport.web.api.identity.entities.UserContactInfo;

import java.util.List;

public interface IUserContactInfoService {

    List<UserContactInfoDto> getUsersContactInfo();
    UserContactInfoDto addUserinfo(UserContactInfoDto userinfo);
    boolean deleteUserinfo(long id);
    UserContactInfoDto updateInfoUser(UserContactInfoDto userInfo);



}
