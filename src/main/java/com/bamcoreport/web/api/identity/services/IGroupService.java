package com.bamcoreport.web.api.identity.services;

import com.bamcoreport.web.api.identity.dto.model.GroupDto;
import com.bamcoreport.web.api.identity.dto.model.RoleDto;

import java.util.List;

public interface IGroupService {
    List<GroupDto> getAllGroups();
    GroupDto addGroup(GroupDto groupDto);
    boolean deleteGroup(long id);
    GroupDto updateGroup(GroupDto groupe);

}
