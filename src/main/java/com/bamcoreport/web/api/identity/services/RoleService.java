package com.bamcoreport.web.api.identity.services;

import com.bamcoreport.web.api.identity.dto.model.RoleDto;
import com.bamcoreport.web.api.identity.dto.model.UserDto;
import com.bamcoreport.web.api.identity.dto.services.IMapClassWithDto;
import com.bamcoreport.web.api.identity.entities.Role;
import com.bamcoreport.web.api.identity.entities.User;
import com.bamcoreport.web.api.identity.helpers.HelpUpdate;
import com.bamcoreport.web.api.identity.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService{

    private static final Logger log = LoggerFactory.getLogger(UserService.class);


    @Autowired
    RoleRepository roleRepository;

    @Autowired
    IMapClassWithDto<Role, RoleDto> roleMapping;

    //---- Get all roles  : --------------------------------------------------------------------

    @Override
    public List<RoleDto> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roleMapping.convertListToListDto(roles, RoleDto.class);
    }

    //---------------------------------------------------------------------------------------------



    //---- Ajouter un role : --------------------------------------------------------------------


    @Override
    public RoleDto addRole(RoleDto role) {
        Role roleEntity=roleMapping.convertToEntity(role,Role.class);
        roleEntity=roleRepository.save(roleEntity);
        return roleMapping.convertToDto(roleEntity, RoleDto.class);
    }

    //---------------------------------------------------------------------------------------------



    //-- Supprimer un role -------------------------------------------------------------------------

    @Override
    public boolean deleteRole(long id) {
        try {
            roleRepository.deleteById(id);
        }catch (Exception ex){
            return false;
        }
        return true;
    }

    //----------------------------------------------------------------------------------------------



    // --- Update un role : -------------------------------------------------------------------------

    @Override
    public RoleDto updateRole(RoleDto role) {
        RoleDto saved = roleMapping.convertToDto(roleRepository.getById(role.getId()),RoleDto.class); // DB
        HelpUpdate.copyNonNullProperties(role,saved);
        Role roleEntity=roleMapping.convertToEntity(saved,Role.class);
        roleEntity=roleRepository.save(roleEntity);
        return roleMapping.convertToDto(roleEntity, RoleDto.class);
    }


    //------------------------------------------------------------------------------------------------




}
