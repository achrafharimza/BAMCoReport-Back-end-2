package com.bamcoreport.web.api.identity.controller;

import com.bamcoreport.web.api.identity.dto.model.UserContactInfoDto;
import com.bamcoreport.web.api.identity.dto.model.UserDto;
import com.bamcoreport.web.api.identity.entities.UserContactInfo;
import com.bamcoreport.web.api.identity.services.IUserContactInfoService;
import com.bamcoreport.web.api.identity.services.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.List;

@RestController
@RequestMapping("/api/identity/contactdata")
@Api(tags = "Utilisateur contact info", value = "Utilisateur contact info Controller")
public class UserContactInfoController {

    private static final Logger log = LoggerFactory.getLogger(UserContactInfoController.class);

    @Autowired
    IUserContactInfoService userContactInfoService;

    //------- All info users : ------------------------------------------------------------

    @GetMapping("/")
    @ApiResponses({ @ApiResponse(code = 500, message = "Une erreur système s'est produite") })
    @ApiOperation(value = "", nickname = "Retourne la liste des informations de contact des utilisateurs", notes = "", tags = {})

    public ResponseEntity<List<UserContactInfoDto>> getAllContactInfos(){
        List<UserContactInfoDto> userContactInfoDto = userContactInfoService.getUsersContactInfo();
        return ResponseEntity.ok(userContactInfoDto);
    }

    //-------------------------------------------------------------------------------------



    //------- delete info user   : ------------------------------------------------------------

    @PostMapping("/addUserInfo")
    @ApiResponses({ @ApiResponse(code = 500, message = "Une erreur système s'est produite") })
    @ApiOperation(value = "", nickname = "Ajouter infos", notes = "", tags = {})

    public ResponseEntity<UserContactInfoDto> addUserinfo(@RequestBody UserContactInfoDto userinfo){
        UserContactInfoDto uct = userContactInfoService.addUserinfo(userinfo);
        return ResponseEntity.ok(uct);
    }


    //------------------------------------------------------------------------------------------


    //----- delete info user : ------------------------------------------------------------------

    @DeleteMapping("/DeleteInfoUser")
    @ApiResponses({ @ApiResponse(code = 500, message = "Une erreur système s'est produite") })
    @ApiOperation(value = "", nickname = "supprimer info user", notes = "", tags = {})

    public ResponseEntity<String> deleteInfoUser(@RequestBody UserContactInfoDto userInfoDto){
        boolean deleted = userContactInfoService.deleteUserinfo(userInfoDto.getId());
        return ResponseEntity.ok("{\"User\":\""+userInfoDto.getId()+"\",\"deleted\":\""+deleted+"\"}" );

    }

    //-------------------------------------------------------------------------------------



    //----- Update Info User ---------------------------------------------------------------

    @PutMapping ("/UpdateInfoUser")
    @ApiResponses({ @ApiResponse(code = 500, message = "Une erreur système s'est produite") })
    @ApiOperation(value = "", nickname = "Update info user", notes = "", tags = {})

    public ResponseEntity<UserContactInfoDto> UpdateInfoUser(@RequestBody UserContactInfoDto userInfoDto){
        UserContactInfoDto uci = userContactInfoService.updateInfoUser(userInfoDto);
        return ResponseEntity.ok(uci);
    }

    //-------------------------------------------------------------------------------------------

}