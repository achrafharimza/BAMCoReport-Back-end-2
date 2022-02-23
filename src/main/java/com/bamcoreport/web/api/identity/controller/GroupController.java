package com.bamcoreport.web.api.identity.controller;


import com.bamcoreport.web.api.identity.dto.model.GroupDto;
import com.bamcoreport.web.api.identity.dto.model.RoleDto;
import com.bamcoreport.web.api.identity.services.IGroupService;
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
@RequestMapping("/api/identity/group")
@Api(tags = "Groupe d'un utilisateur ", value = "Groupe utilisateur controller")

public class GroupController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    IGroupService groupService;


    // ---- gett All groups ------------------------------------------------------------------

    @GetMapping("/")
    @ApiResponses({ @ApiResponse(code = 500, message = "Une erreur système s'est produite") })
    @ApiOperation(value = "", nickname = "Retourne la liste des groups", notes = "", tags = {})

    public ResponseEntity<List<GroupDto>> getListGroups() {
        List<GroupDto> group =groupService.getAllGroups();
        return ResponseEntity.ok(group);
    }

   //---------------------------------------------------------------------------------------------------


    //----- Add Groupe ------------------------------------------------------------------------------

    @PostMapping("/addGroup")
    @ApiResponses({ @ApiResponse(code = 500, message = "Une erreur système s'est produite") })
    @ApiOperation(value = "", nickname = "Ajouter un nouveau groupe ", notes = "", tags = {})

    public ResponseEntity<GroupDto> addGroup(@RequestBody GroupDto groupDto){
        GroupDto grp = groupService.addGroup(groupDto);
        return ResponseEntity.ok(grp);
    }

    //------------------------------------------------------------------------------------------------


//---- Supprimer un role : ------------------------------------------------------------------

    @DeleteMapping("/delete")
    @ApiResponses({ @ApiResponse(code = 500, message = "Une erreur système s'est produite") })
    @ApiOperation(value = "", nickname = "supprimer un groupe", notes = "", tags = {})
    public ResponseEntity<String> deleteGroupe(@RequestBody GroupDto groupeDto){
        boolean deleted = groupService.deleteGroup(groupeDto.getId());
        return ResponseEntity.ok("{\"User\":\""+groupeDto.getId()+"\",\"deleted\":\""+deleted+"\"}" );

    }

    //-----------------------------------------------------------------------------------------------


    // Update un role : -----------------------------------------------------------------------------

    @PutMapping ("/UpdateGroupe")
    @ApiResponses({ @ApiResponse(code = 500, message = "Une erreur système s'est produite") })
    @ApiOperation(value = "", nickname = "Update groupe ", notes = "", tags = {})

    public ResponseEntity<GroupDto> UpdateGroupe(@RequestBody GroupDto groupDto){
        GroupDto gpe = groupService.updateGroup(groupDto);
        return ResponseEntity.ok(gpe);
    }

    //----------------------------------------------------------------------------------------------




}
