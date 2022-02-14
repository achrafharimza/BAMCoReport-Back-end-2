package com.bamcoreport.web.api.identity.controller;

import com.bamcoreport.web.api.identity.dto.model.UserDto;
import com.bamcoreport.web.api.identity.services.IUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;



import java.util.List;

@RestController
@RequestMapping("/api/identity/user")
@Api(tags = "Utilisateur", value = "Utilisateur Controller")
public class UserController {

    static  final org.apache.log4j.Logger log4j = org.apache.log4j.Logger.getLogger(UserController.class.getName());

    @Autowired
    IUserService userService;

    //------- All users : -------------------------------------------------------------------

    @GetMapping("/")
    @ApiOperation(value = "Afficher la liste des utilisateurs ", notes ="Cette methode permet d'afficher une liste des utilisateurs ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Liste utilisateurs trouvé dans BD"),
            @ApiResponse(code = 404, message = "Liste utilisateurs n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")


    })
    public ResponseEntity<List<UserDto>> getAllUsers()  {

        List<UserDto> userDto = userService.getUsers();
        log4j.info("liste des utilisateurs");

        return ResponseEntity.ok(userDto);
    }

    //-------------------------------------------------------------------------------------



    //------- Add user : ------------------------------------------------------------------

    @PostMapping("/addUser")
    @ApiOperation(value = "Ajouter un utilisateur  ", notes ="Cette methode permet d'ajouter un utilisateur")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Utilisateur ajouter a la DB"),
            @ApiResponse(code = 404, message = "Utilisateur n'est pas ajouter a la DB"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })

    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto){
        UserDto uc = userService.addUser(userDto);
        log4j.info("Ajouter un utilisateur");
        return ResponseEntity.ok(uc);
    }

    //-------------------------------------------------------------------------------------



    //------- get info user contact   : ----------------------------------------------------

    @GetMapping("/Userinfo/{id}")
    @ApiOperation(value = "Les informations d'un utilisateur   ", notes ="Cette methode permet de nous donner les informations d'un utilisateur")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Informations d'un utilisateur trouver dans la DB"),
            @ApiResponse(code = 404, message = "Information d'un utilisateur n'existe pas dans la  DB"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })

    public ResponseEntity<UserDto> getinfoUserById(@PathVariable long id) {
        UserDto ud = userService.getById(id);
        log4j.info("afficher les info d'un utilisateur");
        return ResponseEntity.ok(ud);
    }

    //-------------------------------------------------------------------------------------



    //------- Delete user : --------------------------------------------------------------

    @DeleteMapping("/delete")
    @ApiOperation(value = "Supprimer un utilisateur  ", notes ="Cette methode permet de supprimer un utilisateur")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Utilisateur supprimer de la  DB"),
            @ApiResponse(code = 404, message = "Utilisateur n'est pas supprimer de la DB"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })
    public ResponseEntity<String> deleteUser(@RequestBody UserDto userDto){
        boolean deleted = userService.deleteUser(userDto.getId());
        log4j.info("Supprimer un utilisateur");
        return ResponseEntity.ok("{\"User\":\""+userDto.getId()+"\",\"deleted\":\""+deleted+"\"}" );

    }

    //-------------------------------------------------------------------------------------



    //--------- Update User : --------------------------------------------------------------

    @PutMapping ("/UpdateUser")
    @ApiOperation(value = "Updater un utilisateur  ", notes ="Cette methode permet de faire une mise à jour d'un utilisateur")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Utilisateur updater dans la DB"),
            @ApiResponse(code = 404, message = "Utilisateur n'est pas updater dans la DB"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })

    public ResponseEntity<UserDto> UpdateUser(@RequestBody UserDto userDto){
        UserDto uc = userService.updateUser(userDto);
        log4j.info("Updater un utilisateur");
        return ResponseEntity.ok(uc);
    }

    //---------------------------------------------------------------------------------------------





    @PostMapping("/password")
  /*  @ApiResponses({ @ApiResponse(code = 500, message = "Une erreur système s'est produite") })
    @ApiOperation(value = "", nickname = "Ajouter un nouveau utilisateur", notes = "", tags = {})*/

    public ResponseEntity<UserDto>changepass(@RequestBody UserDto userdto ) throws Exception {
        UserDto uc = userService.changepass(userdto);

       if(uc == null){
            return null ;
       }


        return ResponseEntity.ok(uc);

    }


}
