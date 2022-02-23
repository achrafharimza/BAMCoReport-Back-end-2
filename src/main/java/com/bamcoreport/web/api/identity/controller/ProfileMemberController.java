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

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    IProfilMemberService profilMemberService;


    //------- All profils members  : -------------------------------------------------------------------

    @GetMapping("/")
    @ApiResponses({@ApiResponse(code = 500, message = "Une erreur système s'est produite")})
    @ApiOperation(value = "", nickname = "Retourne la liste des profils Members", notes = "", tags = {})

    public ResponseEntity<List<ProfileMemberDto>> getListProfilsMembers() {
        List<ProfileMemberDto> profileMemberDto = profilMemberService.getAllProfilsMembers();
        return ResponseEntity.ok(profileMemberDto);
    }

    //-------------------------------------------------------------------------------------


    //------- Ajouter un role : -------------------------------------------------------------------

    @PostMapping("/addProfilMember")
    @ApiResponses({@ApiResponse(code = 500, message = "Une erreur système s'est produite")})
    @ApiOperation(value = "", nickname = "Ajouter un nouveau profil Member ", notes = "", tags = {})

    public ResponseEntity<ProfileMemberDto> addProfilMember(@RequestBody ProfileMemberDto profileMemberDto) {
        ProfileMemberDto plm = profilMemberService.addProfilMember(profileMemberDto);
        return ResponseEntity.ok(plm);
    }

    //-------------------------------------------------------------------------------------


    //---- Supprimer un profil member : ------------------------------------------------------------------

    @DeleteMapping("/delete")
    @ApiResponses({@ApiResponse(code = 500, message = "Une erreur système s'est produite")})
    @ApiOperation(value = "", nickname = "supprimer un profil", notes = "", tags = {})
    public ResponseEntity<String> deleteProfilMember(@RequestBody ProfileMemberDto profileMemberDto) {
        boolean deleted = profilMemberService.deleteProfilMember(profileMemberDto.getId());
        return ResponseEntity.ok("{\"User\":\"" + profileMemberDto.getId() + "\",\"deleted\":\"" + deleted + "\"}");

    }

    //-----------------------------------------------------------------------------------------------


    // Update un role : -----------------------------------------------------------------------------

    @PutMapping("/UpdateProfilMember")
    @ApiResponses({@ApiResponse(code = 500, message = "Une erreur système s'est produite")})
    @ApiOperation(value = "", nickname = "Update un profil member", notes = "", tags = {})

    public ResponseEntity<ProfileMemberDto> UpdateProfilMember(@RequestBody ProfileMemberDto profileMemberDto) {
        ProfileMemberDto plt = profilMemberService.updateProfilMember(profileMemberDto);
        return ResponseEntity.ok(plt);
    }


//----------------------------------------------------------------------------------------------




}