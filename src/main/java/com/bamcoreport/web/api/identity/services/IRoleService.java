package com.bamcoreport.web.api.identity.services;

import com.bamcoreport.web.api.identity.dto.model.RoleDto;

import java.util.List;

public interface IRoleService {
    List<RoleDto> getAllRoles();
    RoleDto addRole(RoleDto role);
    boolean deleteRole(long id);
    RoleDto updateRole(RoleDto role);

}
