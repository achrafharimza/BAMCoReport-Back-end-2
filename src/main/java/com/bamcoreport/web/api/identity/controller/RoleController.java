package com.bamcoreport.web.api.identity.controller;

import com.bamcoreport.web.api.identity.dto.model.RoleDto;
import com.bamcoreport.web.api.identity.services.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import java.util.List;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/identity/role")
@Api(tags = "Role d'un utilisateur ", value = "Role utilisateur controller")

public class RoleController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    IRoleService roleService;


    //------- All users : -------------------------------------------------------------------

    @GetMapping("/")
    @ApiResponses({ @ApiResponse(code = 500, message = "Une erreur système s'est produite") })
    @ApiOperation(value = "", nickname = "Retourne la liste des roles", notes = "", tags = {})

    public ResponseEntity<List<RoleDto>> getListRoles(){
        List<RoleDto> roleDto = roleService.getAllRoles();
        return ResponseEntity.ok(roleDto);
    }

    //-------------------------------------------------------------------------------------



    //------- Ajouter un role : -------------------------------------------------------------------

    @PostMapping("/addRole")
    @ApiResponses({ @ApiResponse(code = 500, message = "Une erreur système s'est produite") })
    @ApiOperation(value = "", nickname = "Ajouter un nouveau role ", notes = "", tags = {})

    public ResponseEntity<RoleDto> addRole(@RequestBody RoleDto roleDto){
        RoleDto rl = roleService.addRole(roleDto);
        return ResponseEntity.ok(rl);
    }

   //-------------------------------------------------------------------------------------



  //---- Supprimer un role : ------------------------------------------------------------------

    @DeleteMapping("/delete")
    @ApiResponses({ @ApiResponse(code = 500, message = "Une erreur système s'est produite") })
    @ApiOperation(value = "", nickname = "supprimer un utilisateur", notes = "", tags = {})
    public ResponseEntity<String> deleteRole(@RequestBody RoleDto roleDto){
        boolean deleted = roleService.deleteRole(roleDto.getId());
        return ResponseEntity.ok("{\"User\":\""+roleDto.getId()+"\",\"deleted\":\""+deleted+"\"}" );

    }

  //-----------------------------------------------------------------------------------------------



   // Update un role : -----------------------------------------------------------------------------

    @PutMapping ("/UpdateRole")
    @ApiResponses({ @ApiResponse(code = 500, message = "Une erreur système s'est produite") })
    @ApiOperation(value = "", nickname = "Update un role", notes = "", tags = {})

    public ResponseEntity<RoleDto> UpdateRole(@RequestBody RoleDto roleDto){
        RoleDto rl = roleService.updateRole(roleDto);
        return ResponseEntity.ok(rl);
    }

   //----------------------------------------------------------------------------------------------


}
