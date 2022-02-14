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

    static  final org.apache.log4j.Logger log4j = org.apache.log4j.Logger.getLogger(ProfilController.class.getName());

    @Autowired
    IProfileService profileService;


    //------- All profils : -------------------------------------------------------------------

    @GetMapping("/")
    @ApiOperation(value = "Afficher la liste des profils", notes ="Cette methode permet d'afficher une liste des profils ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Liste des profils trouvé dans BD"),
            @ApiResponse(code = 404, message = "Liste des profils  n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")


    })

    public ResponseEntity<List<ProfileDto>> getListProfils() {
        List<ProfileDto> profileDto = profileService.getAllProfils();
        log4j.info("Liste des profils");
        return ResponseEntity.ok(profileDto);
    }

    //-------------------------------------------------------------------------------------


    //------- Ajouter un role : -------------------------------------------------------------------

    @PostMapping("/addProfil")
    @ApiOperation(value = "Ajouter un profil", notes ="Cette methode permet d'ajouter un profil")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Profil ajouter a la DB"),
            @ApiResponse(code = 404, message = "Profil n'est pas ajouter a la DB"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })

    public ResponseEntity<ProfileDto> addProfil(@RequestBody ProfileDto profileDto) {
      ProfileDto pl = profileService.addProfil(profileDto);
        log4j.info("Ajouter un profil");
        return ResponseEntity.ok(pl);
    }

    //-------------------------------------------------------------------------------------


    //---- Supprimer un role : ------------------------------------------------------------------

    @DeleteMapping("/delete")
    @ApiOperation(value = "Supprimer un profil", notes ="Cette methode permet de supprimer un profil")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Profil supprimer de la  DB"),
            @ApiResponse(code = 404, message = "Profil n'est pas supprimer de la DB"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })

    public ResponseEntity<String> deleteProfil(@RequestBody ProfileDto profileDto) {
        boolean deleted = profileService.deleteProfil(profileDto.getId());
        log4j.info("Supprimer un profil");
        return ResponseEntity.ok("{\"Profil\":\"" + profileDto.getId() + "\",\"deleted\":\"" + deleted + "\"}");

    }

    //-----------------------------------------------------------------------------------------------


    // Update un role : -----------------------------------------------------------------------------

    @PutMapping("/UpdateProfil")
    @ApiOperation(value = "Updater un profil", notes ="Cette methode permet de faire une mise à jour d'un profil")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Profil updater dans la DB"),
            @ApiResponse(code = 404, message = "Profil n'est pas updater dans la DB"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })

    public ResponseEntity<ProfileDto> UpdateProfil(@RequestBody ProfileDto profileDto) {
        ProfileDto pl = profileService.updateProfil(profileDto);
        log4j.info("Updater un profil");
        return ResponseEntity.ok(pl);
    }


//----------------------------------------------------------------------------------------------
}