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

    static  final org.apache.log4j.Logger log4j = org.apache.log4j.Logger.getLogger(RoleController.class.getName());

    @Autowired
    IRoleService roleService;


    //------- All users : -------------------------------------------------------------------

    @GetMapping("/")
    @ApiOperation(value = "Afficher la liste des roles  ", notes ="Cette methode permet d'afficher une liste des roles ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Liste des roles trouvé dans BD"),
            @ApiResponse(code = 404, message = "Liste des roles  n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")


    })

    public ResponseEntity<List<RoleDto>> getListRoles(){
        List<RoleDto> roleDto = roleService.getAllRoles();
        log4j.info("Liste des roles");
        return ResponseEntity.ok(roleDto);
    }

    //-------------------------------------------------------------------------------------



    //------- Ajouter un role : -------------------------------------------------------------------

    @PostMapping("/addRole")
    @ApiOperation(value = "Ajouter un role", notes ="Cette methode permet d'ajouter un role")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Role ajouter a la DB"),
            @ApiResponse(code = 404, message = "Role n'est pas ajouter a la DB"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })

    public ResponseEntity<RoleDto> addRole(@RequestBody RoleDto roleDto){
        RoleDto rl = roleService.addRole(roleDto);
        log4j.info("Ajouter un role");
        return ResponseEntity.ok(rl);
    }

   //-------------------------------------------------------------------------------------



  //---- Supprimer un role : ------------------------------------------------------------------

    @DeleteMapping("/delete")
    @ApiOperation(value = "Supprimer un role", notes ="Cette methode permet de supprimer un role")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Role supprimer de la  DB"),
            @ApiResponse(code = 404, message = "Role n'est pas supprimer de la DB"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })

    public ResponseEntity<String> deleteRole(@RequestBody RoleDto roleDto){
        boolean deleted = roleService.deleteRole(roleDto.getId());
        log4j.info("Supprimer un role");
        return ResponseEntity.ok("{\"Role\":\""+roleDto.getId()+"\",\"deleted\":\""+deleted+"\"}" );

    }

  //-----------------------------------------------------------------------------------------------



   // Update un role : -----------------------------------------------------------------------------

    @PutMapping ("/UpdateRole")
    @ApiOperation(value = "Updater un role", notes ="Cette methode permet de faire une mise à jour d'un role")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Role updater dans la DB"),
            @ApiResponse(code = 404, message = "Role n'est pas updater dans la DB"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })

    public ResponseEntity<RoleDto> UpdateRole(@RequestBody RoleDto roleDto){
        RoleDto rl = roleService.updateRole(roleDto);
        log4j.info("Updater un role");
        return ResponseEntity.ok(rl);
    }

   //----------------------------------------------------------------------------------------------


}
