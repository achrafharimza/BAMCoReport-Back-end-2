package com.bamcoreport.web.api.identity.controller;


import com.bamcoreport.web.api.identity.dto.model.UserDto;
import com.bamcoreport.web.api.identity.dto.model.UserMembershipDto;
import com.bamcoreport.web.api.identity.services.IUserMembershipService;
import com.bamcoreport.web.api.identity.services.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/identity/UserMembership")
@Api(tags = "UserMembership", value = "UserMembership")
public class UserMembershipController {

    static  final org.apache.log4j.Logger log4j = org.apache.log4j.Logger.getLogger(UserMembershipController.class.getName());

    @Autowired
    IUserMembershipService UserMembershipService;
    //------- All UserMemberships : -------------------------------------------------------------------

    @GetMapping("/")
    @ApiOperation(value = "Afficher la liste des users Membership ", notes ="Cette methode permet d'afficher une liste des users Membership ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Liste users Membership  trouvé dans BD"),
            @ApiResponse(code = 404, message = "Liste users Membership n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")


    })
    public ResponseEntity<List<UserMembershipDto>> getAllUserMemberships(){
        List<UserMembershipDto> UserMembershipDto = UserMembershipService.getUserMemberships();
        log4j.info("liste des membership");
        return ResponseEntity.ok(UserMembershipDto);
    }

    //-------------------------------------------------------------------------------------


    //------- Add UserMembership : ------------------------------------------------------------------

    @PostMapping("/add")
    @ApiOperation(value = "Ajouter un user Membership  ", notes ="Cette methode permet d'ajouter un user Membership")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User Membership ajouter a la DB"),
            @ApiResponse(code = 404, message = "User Membership  n'est pas ajouter a la DB"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })

    public ResponseEntity<UserMembershipDto> addUserMembership(@RequestBody UserMembershipDto userMembershipDto){
        UserMembershipDto uc = UserMembershipService.addUserMembership(userMembershipDto);
        log4j.info("Ajouter un membership");
        return ResponseEntity.ok(uc);
    }

    //-------------------------------------------------------------------------------------


    //------- Delete UserMembership : --------------------------------------------------------------

    @DeleteMapping("/delete")
    @ApiOperation(value = "Supprimer un User Membership  ", notes ="Cette methode permet de supprimer un User Membership")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User Membership supprimer de la  DB"),
            @ApiResponse(code = 404, message = "User Membership n'est pas supprimer de la DB"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })
    public ResponseEntity<String> deleteUser(@RequestBody UserMembershipDto userMembershipDto){
        boolean deleted = UserMembershipService.deleteUserMembership(userMembershipDto.getId());
        log4j.info("Supprimer un user membership");
        return ResponseEntity.ok("{\"UserMembership\":\""+userMembershipDto.getId()+"\",\"deleted\":\""+deleted+"\"}" );

    }

    //-------------------------------------------------------------------------------------


//--------- Update UserMembership : --------------------------------------------------------------

    @PutMapping ("/update")
    @ApiOperation(value = "Updater un User Membership", notes ="Cette methode permet de faire une mise à jour d'un User Membership")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User Membership updater dans la DB"),
            @ApiResponse(code = 404, message = "User Membership n'est pas updater dans la DB"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })

    public ResponseEntity<UserMembershipDto> update(@RequestBody UserMembershipDto userMembershipDto){
        UserMembershipDto uc = UserMembershipService.updateuserMembership(userMembershipDto);
        log4j.info("updater un user membership");
        return ResponseEntity.ok(uc);
    }

    //---------------------------------------------------------------------------------------------
}
