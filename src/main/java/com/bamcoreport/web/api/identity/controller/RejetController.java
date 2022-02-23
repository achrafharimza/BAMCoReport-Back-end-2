package com.bamcoreport.web.api.identity.controller;

import com.bamcoreport.web.api.identity.config.SecurityConstants;
import com.bamcoreport.web.api.identity.dto.model.RejetDto;
import com.bamcoreport.web.api.identity.dto.model.RejetParUtilisateurDto;
import com.bamcoreport.web.api.identity.helpers.UploadFileResponse;
import com.bamcoreport.web.api.identity.services.IRejetService;
import com.bamcoreport.web.api.identity.services.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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


    @Autowired
    IUserService userService;


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

    public ResponseEntity<List<RejetDto>> getListRejets(HttpServletRequest req){

        String token = req.getHeader(SecurityConstants.HEADER_STRING);
        System.out.println(token);
        System.out.println("tokentokentokentokentokentokentokentokentoken");
       String role = userService.filter(token);
        System.out.println(role);
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

    public ResponseEntity<RejetDto> addRejet(@RequestBody RejetDto rejetDto,HttpServletRequest req) throws Exception {
        String token = req.getHeader(SecurityConstants.HEADER_STRING);

        String role = userService.filter(token);


        if (role.equals("admin") || role.equals("super admin") ){
            RejetDto rj = rejetService.addRejet(rejetDto);
//        log4j.info("Ajouter un rejet");
            return ResponseEntity.ok(rj);
        }
        else{
            throw new Exception("you dont have any permission for this request");
        }

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

    public ResponseEntity<RejetDto> Update(@RequestBody RejetDto rejetDto,HttpServletRequest req) throws Exception {
        String token = req.getHeader(SecurityConstants.HEADER_STRING);

        String role = userService.filter(token);
        if (role.equals("admin") || role.equals("super admin") ){
            RejetDto uc = rejetService.updateRejet(rejetDto);
            return new ResponseEntity<RejetDto>(uc, HttpStatus.CREATED);
        }
        else{
            throw new Exception("you dont have any permission for this request");
        }

    }

    //---------------------------------------------------------------------------------------------

    //--------- upload file : --------------------------------------------------------------
    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = rejetService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,

                file.getContentType(), file.getSize());
    }
    //--------- nombre total Rejet : --------------------------------------------------------------
    @GetMapping("/total")

    public ResponseEntity<Integer> totalRejet() throws Exception {
        int Nombre_Total = rejetService.totalRejet();

            return ResponseEntity.ok(Nombre_Total);

    }
    //--------- Nombre des rejets par utilisateur : --------------------------------------------------------------

    @GetMapping("/ParUser")

    public ResponseEntity<List<RejetParUtilisateurDto>> RejetParUser()  {
        List<RejetParUtilisateurDto> rejparnom = rejetService.rejetPuser();

        return ResponseEntity.ok(rejparnom);

    }




}