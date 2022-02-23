package com.bamcoreport.web.api.identity.controller;

import com.bamcoreport.web.api.identity.config.SecurityConstants;
import com.bamcoreport.web.api.identity.dto.model.UserDto;
import com.bamcoreport.web.api.identity.exceptions.ErrorMessages;
import com.bamcoreport.web.api.identity.services.IUserService;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/identity/user")
@Api(tags = "Utilisateur", value = "Utilisateur Controller")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    IUserService userService;

    //------- All users : -------------------------------------------------------------------

    @GetMapping("/all")
    @ApiResponses({ @ApiResponse(code = 500, message = "Une erreur système s'est produite") })
    @ApiOperation(value = "", nickname = "Retourne la liste des utilisateurs", notes = "", tags = {})
    public ResponseEntity<List<UserDto>> getAllUsers(@RequestParam(value ="page" ,defaultValue = "0") int page ,@RequestParam(value ="limit",defaultValue = "5")int limit){
        List<UserDto> userDto = userService.getUsers(page, limit);
        return ResponseEntity.ok(userDto);
    }

    //-------------------------------------------------------------------------------------

    //------- Add user : ------------------------------------------------------------------

    @PostMapping("/password")
  /*  @ApiResponses({ @ApiResponse(code = 500, message = "Une erreur système s'est produite") })
    @ApiOperation(value = "", nickname = "Ajouter un nouveau utilisateur", notes = "", tags = {})*/

    public UserDto changepass(@RequestBody UserDto userDto ) {
        UserDto uc = userService.changepass(userDto);

//        if(uc == null){
//            return "password not changed";
//        }


            return uc;




    }

    //-------------------------------------------------------------------------------------

    //------- Add user : ------------------------------------------------------------------

    @PostMapping("/addUser")
  /*  @ApiResponses({ @ApiResponse(code = 500, message = "Une erreur système s'est produite") })
    @ApiOperation(value = "", nickname = "Ajouter un nouveau utilisateur", notes = "", tags = {})*/

    public ResponseEntity<UserDto> addUser(@RequestBody @Valid  UserDto userDto ) throws Exception {

        if(userDto.getFirstname().isEmpty()) throw new Exception(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        UserDto uc = userService.addUser(userDto);

        return new ResponseEntity<UserDto>(uc, HttpStatus.CREATED);

    }

    //-------------------------------------------------------------------------------------



    //------- get info user contact   : ----------------------------------------------------

    @GetMapping("/getById/{id}")
    @ApiResponses({ @ApiResponse(code = 500, message = "Une erreur système s'est produite") })
    @ApiOperation(value = "", nickname = "get info user", notes = "", tags = {})

    public ResponseEntity<UserDto> getinfoUserById(@PathVariable long id) throws Exception {
        UserDto ud = userService.getById(id);
        return ResponseEntity.ok(ud);
    }

    //-------------------------------------------------------------------------------------



    //------- Delete user : --------------------------------------------------------------

    @DeleteMapping("/delete/{id}")
    @ApiResponses({ @ApiResponse(code = 500, message = "Une erreur système s'est produite") })
    @ApiOperation(value = "", nickname = "supprimer un utilisateur", notes = "", tags = {})
    public ResponseEntity<String> deleteUser(@PathVariable long id){
        boolean deleted = userService.deleteUser(id);
        return new ResponseEntity<String>("{\"User\":\""+id+"\",\"deleted\":\""+deleted+"\"}", HttpStatus.OK);

    }

    //-------------------------------------------------------------------------------------



    //--------- Update User : --------------------------------------------------------------

    @PutMapping ("/update")
    @ApiResponses({ @ApiResponse(code = 500, message = "Une erreur système s'est produite") })
    @ApiOperation(value = "", nickname = "Update un user", notes = "", tags = {})

    public ResponseEntity<UserDto> UpdateUser(@RequestBody UserDto userDto) throws Exception {
        UserDto uc = userService.updateUser(userDto);
        return new ResponseEntity<UserDto>(uc, HttpStatus.CREATED);
    }

    //---------------------------------------------------------------------------------------------




}
