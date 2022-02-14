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

    static  final org.apache.log4j.Logger log4j = org.apache.log4j.Logger.getLogger(UserContactInfoController.class.getName());

    @Autowired
    IUserContactInfoService userContactInfoService;

    //------- All info users : ------------------------------------------------------------

    @GetMapping("/")
    @ApiOperation(value = "Afficher la liste des informations des utilisateurs ", notes ="Cette methode permet d'afficher une liste des informations des utilisateurs ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Liste des informations des utilisateurs trouvé dans BD"),
            @ApiResponse(code = 404, message = "Liste des informations des utilisateurs n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")


    })

    public ResponseEntity<List<UserContactInfoDto>> getAllContactInfos(){
        List<UserContactInfoDto> userContactInfoDto = userContactInfoService.getUsersContactInfo();
        log4j.info("liste des informations des utilisateurs");
        return ResponseEntity.ok(userContactInfoDto);
    }

    //-------------------------------------------------------------------------------------



    //------- delete info user   : ------------------------------------------------------------

    @PostMapping("/addUserInfo")
    @ApiOperation(value = "Ajouter les informations d'un utilisateur", notes ="Cette methode permet d'ajouter les informations d'un utilisateur")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Information ajouter a la DB"),
            @ApiResponse(code = 404, message = "Information n'est pas ajouter a la DB"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })

    public ResponseEntity<UserContactInfoDto> addUserinfo(@RequestBody UserContactInfoDto userinfo){
        UserContactInfoDto uct = userContactInfoService.addUserinfo(userinfo);
        log4j.info("Ajout des informations d'un utilisateur");
        return ResponseEntity.ok(uct);
    }


    //------------------------------------------------------------------------------------------


    //----- delete info user : ------------------------------------------------------------------

    @DeleteMapping("/DeleteInfoUser")
    @ApiOperation(value = "Supprimer les informations d'un utilisateur  ", notes ="Cette methode permet de supprimer les informations d'un utilisateur")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Information  supprimer de la  DB"),
            @ApiResponse(code = 404, message = "Information n'est pas supprimer de la DB"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })

    public ResponseEntity<String> deleteInfoUser(@RequestBody UserContactInfoDto userInfoDto){
        boolean deleted = userContactInfoService.deleteUserinfo(userInfoDto.getId());
        log4j.info("Supprimer les infos d'un utilisateur");
        return ResponseEntity.ok("{\"UserContactInfo\":\""+userInfoDto.getId()+"\",\"deleted\":\""+deleted+"\"}" );

    }

    //-------------------------------------------------------------------------------------



    //----- Update Info User ---------------------------------------------------------------

    @PutMapping ("/UpdateInfoUser")
    @ApiOperation(value = "Updater les informations d'un utilisateur  ", notes ="Cette methode permet de faire une mise à jour des informations d'un utilisateur")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Information updater dans la DB"),
            @ApiResponse(code = 404, message = "Utilisateur n'est pas updater dans la DB"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })
    public ResponseEntity<UserContactInfoDto> UpdateInfoUser(@RequestBody UserContactInfoDto userInfoDto){
        UserContactInfoDto uci = userContactInfoService.updateInfoUser(userInfoDto);
        log4j.info("Updater les informations d'un utilisateur");
        return ResponseEntity.ok(uci);
    }

    //-------------------------------------------------------------------------------------------

}