package com.bamcoreport.web.api.identity.controller;

import com.bamcoreport.web.api.identity.dto.model.ProfileDto;
import com.bamcoreport.web.api.identity.dto.model.RoleDto;
import com.bamcoreport.web.api.identity.services.IProfileService;
import com.bamcoreport.web.api.identity.services.IRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/identity/profile")
@Api(tags = "Profil d'un utilisateur ", value = "Profil utilisateur controller")

public class ProfilController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    IProfileService profileService;


    //------- All profils : -------------------------------------------------------------------

    @GetMapping("/")
    @ApiResponses({@ApiResponse(code = 500, message = "Une erreur système s'est produite")})
    @ApiOperation(value = "", nickname = "Retourne la liste des profils", notes = "", tags = {})

    public ResponseEntity<List<ProfileDto>> getListProfils() {
        List<ProfileDto> profileDto = profileService.getAllProfils();
        return ResponseEntity.ok(profileDto);
    }

    //-------------------------------------------------------------------------------------


    //------- Ajouter un role : -------------------------------------------------------------------

    @PostMapping("/addProfil")
    @ApiResponses({@ApiResponse(code = 500, message = "Une erreur système s'est produite")})
    @ApiOperation(value = "", nickname = "Ajouter un nouveau profil ", notes = "", tags = {})

    public ResponseEntity<ProfileDto> addProfil(@RequestBody ProfileDto profileDto) {
      ProfileDto pl = profileService.addProfil(profileDto);
        return ResponseEntity.ok(pl);
    }

    //-------------------------------------------------------------------------------------


    //---- Supprimer un role : ------------------------------------------------------------------

    @DeleteMapping("/delete")
    @ApiResponses({@ApiResponse(code = 500, message = "Une erreur système s'est produite")})
    @ApiOperation(value = "", nickname = "supprimer un profil", notes = "", tags = {})
    public ResponseEntity<String> deleteProfil(@RequestBody ProfileDto profileDto) {
        boolean deleted = profileService.deleteProfil(profileDto.getId());
        return ResponseEntity.ok("{\"User\":\"" + profileDto.getId() + "\",\"deleted\":\"" + deleted + "\"}");

    }

    //-----------------------------------------------------------------------------------------------


    // Update un role : -----------------------------------------------------------------------------

    @PutMapping("/UpdateProfil")
    @ApiResponses({@ApiResponse(code = 500, message = "Une erreur système s'est produite")})
    @ApiOperation(value = "", nickname = "Update un profil", notes = "", tags = {})

    public ResponseEntity<ProfileDto> UpdateProfil(@RequestBody ProfileDto profileDto) {
        ProfileDto pl = profileService.updateProfil(profileDto);
        return ResponseEntity.ok(pl);
    }


//----------------------------------------------------------------------------------------------
}