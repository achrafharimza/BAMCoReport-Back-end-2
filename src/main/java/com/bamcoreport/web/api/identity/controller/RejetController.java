package com.bamcoreport.web.api.identity.controller;

import com.bamcoreport.web.api.identity.dto.model.ProfileDto;
import com.bamcoreport.web.api.identity.dto.model.RejetDto;
import com.bamcoreport.web.api.identity.dto.model.RoleDto;
import com.bamcoreport.web.api.identity.dto.model.UserDto;
import com.bamcoreport.web.api.identity.services.IProfileService;
import com.bamcoreport.web.api.identity.services.IRejetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
/*@RequestMapping("/api/identity/rejet/" +
        "")*/
@RequestMapping("/api/identity/rejet/")
@Api(tags = "Rejet  d'un utilisateur ", value = "Rejet controller")

public class RejetController {

//    static  final org.apache.log4j.Logger log4j = org.apache.log4j.Logger.getLogger(RejetController.class.getName());

    @Autowired
    IRejetService rejetService;



    //------- All rejets : -------------------------------------------------------------------

    @GetMapping("/")
    @ApiOperation(value = "Afficher la liste des rejts  ", notes ="Cette methode permet d'afficher une liste des rejets ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Liste des rejets trouvé dans BD"),
            @ApiResponse(code = 404, message = "Liste des rejets  n'existe pas dans BD"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")


    })

    public ResponseEntity<List<RejetDto>> getListRejets(){
        List<RejetDto> rejet =rejetService.getAllRejets();
//        log4j.info("Liste des rejets");
        return ResponseEntity.ok(rejet);
    }

    //-------------------------------------------------------------------------------------


    //------- Ajouter un rejet : -------------------------------------------------------------------

    @PostMapping("/addRejet")
    @ApiOperation(value = "Ajouter un rejet", notes ="Cette methode permet d'ajouter un rejet")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Rejet ajouter a la DB"),
            @ApiResponse(code = 404, message = "Rejet n'est pas ajouter a la DB"),
            @ApiResponse(code = 500, message = "Une erreur système s'est produite"),
            @ApiResponse(code = 401, message = "Pas d'autorisation"),
            @ApiResponse(code = 403, message = "Acces interdit")
    })

    public ResponseEntity<RejetDto> addRejet(@RequestBody RejetDto rejetDto) {
        RejetDto rj = rejetService.addRejet(rejetDto);
//        log4j.info("Ajouter un rejet");
        return ResponseEntity.ok(rj);
    }

    //-------------------------------------------------------------------------------------
    //------- get one rejet : ----------------------------------------------------

    @GetMapping("/getById/{id}")
    @ApiResponses({ @ApiResponse(code = 500, message = "Une erreur système s'est produite") })
    @ApiOperation(value = "", nickname = "get info user", notes = "", tags = {})

    public ResponseEntity<RejetDto> getById(@PathVariable long id) throws Exception {
        RejetDto ud = rejetService.getById(id);
        return ResponseEntity.ok(ud);
    }

    //-------------------------------------------------------------------------------------
    //------- Delete rejet : --------------------------------------------------------------

    @DeleteMapping("/delete/{id}")
    @ApiResponses({ @ApiResponse(code = 500, message = "Une erreur système s'est produite") })
    @ApiOperation(value = "", nickname = "supprimer un utilisateur", notes = "", tags = {})
    public ResponseEntity<String> delete(@PathVariable long id){
        boolean deleted = rejetService.deleteRejet(id);
        return new ResponseEntity<String>("{\"User\":\""+id+"\",\"deleted\":\""+deleted+"\"}", HttpStatus.OK);

    }

    //-------------------------------------------------------------------------------------
    //--------- Update rejet : --------------------------------------------------------------

    @PutMapping ("/update")
    @ApiResponses({ @ApiResponse(code = 500, message = "Une erreur système s'est produite") })
    @ApiOperation(value = "", nickname = "Update un user", notes = "", tags = {})

    public ResponseEntity<RejetDto> Update(@RequestBody RejetDto rejetDto) throws Exception {
        RejetDto uc = rejetService.updateRejet(rejetDto);
        return new ResponseEntity<RejetDto>(uc, HttpStatus.CREATED);
    }

    //---------------------------------------------------------------------------------------------

}