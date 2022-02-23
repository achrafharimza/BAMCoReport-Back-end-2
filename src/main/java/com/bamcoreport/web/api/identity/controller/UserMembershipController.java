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


    @Autowired
    IUserMembershipService UserMembershipService;
    //------- All UserMemberships : -------------------------------------------------------------------

    @GetMapping("/")
    @ApiResponses({ @ApiResponse(code = 500, message = "Une erreur système s'est produite") })
    @ApiOperation(value = "", nickname = "Retourne la liste des UserMembership", notes = "", tags = {})
    public ResponseEntity<List<UserMembershipDto>> getAllUserMemberships(){
        List<UserMembershipDto> UserMembershipDto = UserMembershipService.getUserMemberships();
        return ResponseEntity.ok(UserMembershipDto);
    }

    //-------------------------------------------------------------------------------------


    //------- Add UserMembership : ------------------------------------------------------------------

    @PostMapping("/add")
    @ApiResponses({ @ApiResponse(code = 500, message = "Une erreur système s'est produite") })
    @ApiOperation(value = "", nickname = "Ajouter un nouveau UserMembership", notes = "", tags = {})

    public ResponseEntity<UserMembershipDto> addUserMembership(@RequestBody UserMembershipDto userMembershipDto){
        UserMembershipDto uc = UserMembershipService.addUserMembership(userMembershipDto);
        return ResponseEntity.ok(uc);
    }

    //-------------------------------------------------------------------------------------


    //------- Delete UserMembership : --------------------------------------------------------------

    @DeleteMapping("/delete")
    @ApiResponses({ @ApiResponse(code = 500, message = "Une erreur système s'est produite") })
    @ApiOperation(value = "", nickname = "supprimer un UserMembership", notes = "", tags = {})
    public ResponseEntity<String> deleteUser(@RequestBody UserMembershipDto userMembershipDto){
        boolean deleted = UserMembershipService.deleteUserMembership(userMembershipDto.getId());
        return ResponseEntity.ok("{\"User\":\""+userMembershipDto.getId()+"\",\"deleted\":\""+deleted+"\"}" );

    }

    //-------------------------------------------------------------------------------------


//--------- Update UserMembership : --------------------------------------------------------------

    @PutMapping ("/update")
    @ApiResponses({ @ApiResponse(code = 500, message = "Une erreur système s'est produite") })
    @ApiOperation(value = "", nickname = "MODIFIER UserMembership", notes = "", tags = {})

    public ResponseEntity<UserMembershipDto> update(@RequestBody UserMembershipDto userMembershipDto){
        UserMembershipDto uc = UserMembershipService.updateuserMembership(userMembershipDto);
        return ResponseEntity.ok(uc);
    }

    //---------------------------------------------------------------------------------------------
}
