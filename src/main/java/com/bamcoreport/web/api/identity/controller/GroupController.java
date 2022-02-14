package com.bamcoreport.web.api.identity.controller;


import com.bamcoreport.web.api.identity.dto.model.GroupDto;
import com.bamcoreport.web.api.identity.services.IGroupService;
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
@RequestMapping("/api/identity/group")
@Api(tags = "Groupe d'un utilisateur ", value = "Groupe utilisateur controller")

public class GroupController {

    static  final org.apache.log4j.Logger log4j = org.apache.log4j.Logger.getLogger(GroupController.class.getName());

    @Autowired
    IGroupService groupService;


    // ---- gett All groups ------------------------------------------------------------------

    @GetMapping("/")
    @ApiOperation(value = "Afficher la liste des groups", notes ="Cette methode permet d'afficher une liste des groups ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Liste des groups trouvé dans BD"),
            @ApiResponse(code = 404, message = "Liste des groups n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")


    })

    public ResponseEntity<List<GroupDto>> getListGroups() {
        List<GroupDto> group =groupService.getAllGroups();
        log4j.info("Liste des groupes");
        return ResponseEntity.ok(group);
    }

   //---------------------------------------------------------------------------------------------------


    //----- Add Groupe ------------------------------------------------------------------------------

    @PostMapping("/addGroup")
    @ApiOperation(value = "Ajouter un groupe", notes ="Cette methode permet d'ajouter un groupe")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Groupe ajouter a la DB"),
            @ApiResponse(code = 404, message = "Groupe n'est pas ajouter a la DB"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })


    public ResponseEntity<GroupDto> addGroup(@RequestBody GroupDto groupDto){
        GroupDto grp = groupService.addGroup(groupDto);
        log4j.info("Ajouter un groupe");
        return ResponseEntity.ok(grp);
    }

    //------------------------------------------------------------------------------------------------


//---- Supprimer un role : ------------------------------------------------------------------

    @DeleteMapping("/delete")
    @ApiOperation(value = "Supprimer un groupe", notes ="Cette methode permet de supprimer un groupe")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Groupe supprimer de la  DB"),
            @ApiResponse(code = 404, message = "Groupe n'est pas supprimer de la DB"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })
    public ResponseEntity<String> deleteGroupe(@RequestBody GroupDto groupeDto){
        boolean deleted=groupService.deleteGroup(groupeDto.getId());
        log4j.info("Supprimer un groupe");
        return ResponseEntity.ok("{\"Groupe\":\""+groupeDto.getId()+"\",\"deleted\":\""+deleted+"\"}" );
    }


    //-----------------------------------------------------------------------------------------------


    // Update un role : -----------------------------------------------------------------------------

    @PutMapping ("/UpdateGroupe")
    @ApiOperation(value = "Updater un groupe", notes ="Cette methode permet de faire une mise à jour d'un groupe")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Groupe updater dans la DB"),
            @ApiResponse(code = 404, message = "Groupe n'est pas updater dans la DB"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })

    public ResponseEntity<GroupDto> UpdateGroupe(@RequestBody GroupDto groupDto){
        GroupDto gpe = groupService.updateGroup(groupDto);
        log4j.info("Updater un groupe");
        return ResponseEntity.ok(gpe);
    }

    //----------------------------------------------------------------------------------------------




}
