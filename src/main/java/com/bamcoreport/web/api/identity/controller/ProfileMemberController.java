package com.bamcoreport.web.api.identity.controller;

import com.bamcoreport.web.api.identity.dto.model.ProfileMemberDto;
import com.bamcoreport.web.api.identity.dto.model.RoleDto;
import com.bamcoreport.web.api.identity.services.IProfilMemberService;
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
@RequestMapping("/api/identity/profileMember")
@Api(tags = "Profile member d'un utilisateur ", value = "Profile member utilisateur controller")


public class ProfileMemberController {

    static  final org.apache.log4j.Logger log4j = org.apache.log4j.Logger.getLogger(ProfileMemberController.class.getName());

    @Autowired
    IProfilMemberService profilMemberService;


    //------- All profils members  : -------------------------------------------------------------------

    @GetMapping("/")
    @ApiOperation(value = "Afficher la liste des profils members", notes ="Cette methode permet d'afficher une liste des profils memebers ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Liste des profils members trouvé dans BD"),
            @ApiResponse(code = 404, message = "Liste des profils members n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")


    })

    public ResponseEntity<List<ProfileMemberDto>> getListProfilsMembers() {
        List<ProfileMemberDto> profileMemberDto = profilMemberService.getAllProfilsMembers();
        log4j.info("Liste des profils members");
        return ResponseEntity.ok(profileMemberDto);
    }

    //-------------------------------------------------------------------------------------


    //------- Ajouter un role : -------------------------------------------------------------------

    @PostMapping("/addProfilMember")

    @ApiOperation(value = "Ajouter un profil member", notes ="Cette methode permet d'ajouter un profil member")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Profil member ajouter a la DB"),
            @ApiResponse(code = 404, message = "Profil member n'est pas ajouter a la DB"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })

    public ResponseEntity<ProfileMemberDto> addProfilMember(@RequestBody ProfileMemberDto profileMemberDto) {
        ProfileMemberDto plm = profilMemberService.addProfilMember(profileMemberDto);
        log4j.info("Ajouter un profil member");
        return ResponseEntity.ok(plm);
    }

    //-------------------------------------------------------------------------------------


    //---- Supprimer un profil member : ------------------------------------------------------------------

    @DeleteMapping("/delete")
    @ApiOperation(value = "Supprimer un profil member", notes ="Cette methode permet de supprimer un profil memeber")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Profil memeber supprimer de la  DB"),
            @ApiResponse(code = 404, message = "Profil member n'est pas supprimer de la DB"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })
    public ResponseEntity<String> deleteProfilMember(@RequestBody ProfileMemberDto profileMemberDto) {
        boolean deleted = profilMemberService.deleteProfilMember(profileMemberDto.getId());
        log4j.info("Supprimer un profil member");
        return ResponseEntity.ok("{\"ProfilMember\":\"" + profileMemberDto.getId() + "\",\"deleted\":\"" + deleted + "\"}");

    }

    //-----------------------------------------------------------------------------------------------


    // Update un role : -----------------------------------------------------------------------------

    @PutMapping("/UpdateProfilMember")
    @ApiOperation(value = "Updater un profil member", notes ="Cette methode permet de faire une mise à jour d'un profil member")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Profil member updater dans la DB"),
            @ApiResponse(code = 404, message = "Profil member n'est pas updater dans la DB"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })
    public ResponseEntity<ProfileMemberDto> UpdateProfilMember(@RequestBody ProfileMemberDto profileMemberDto) {
        ProfileMemberDto plt = profilMemberService.updateProfilMember(profileMemberDto);
        log4j.info("Updater un profil member");
        return ResponseEntity.ok(plt);
    }


//----------------------------------------------------------------------------------------------




}