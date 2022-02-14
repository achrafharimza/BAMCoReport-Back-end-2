package com.bamcoreport.web.api.identity.services;

import com.bamcoreport.web.api.identity.dto.model.UserMembershipDto;

import java.util.List;

public interface IUserMembershipService {
    List<UserMembershipDto> getUserMemberships();
    UserMembershipDto addUserMembership(UserMembershipDto userMembershipDto);
    UserMembershipDto getById(long id);
    boolean deleteUserMembership(long id);
    UserMembershipDto updateuserMembership(UserMembershipDto userMembershipDto);
}
