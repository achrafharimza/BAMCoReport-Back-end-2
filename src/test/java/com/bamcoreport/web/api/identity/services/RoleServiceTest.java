package com.bamcoreport.web.api.identity.services;

import com.bamcoreport.web.api.identity.dto.model.RoleDto;
import com.bamcoreport.web.api.identity.entities.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class RoleServiceTest {
    @InjectMocks
    IRoleService roleService;
    @Test
    void addRole() {
//        RoleService roleService =new RoleService();
        User user =new User();
        user.setId(1L);
        RoleDto roleDto= new RoleDto();
        roleDto.setName("role77");
        roleDto.setDisplayName("role77");
        roleDto.setDescription("role77");
        roleDto.setCreatedBy(user);



        RoleDto flag= roleService.addRole(roleDto);

        assertNotNull(flag);
    }

}