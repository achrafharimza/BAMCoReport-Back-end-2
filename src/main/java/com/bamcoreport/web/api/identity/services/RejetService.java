package com.bamcoreport.web.api.identity.services;

import com.bamcoreport.web.api.identity.dto.model.RejetDto;
import com.bamcoreport.web.api.identity.dto.model.RejetParUtilisateurDto;
import com.bamcoreport.web.api.identity.dto.services.IMapClassWithDto;
import com.bamcoreport.web.api.identity.entities.Rejet;
import com.bamcoreport.web.api.identity.exceptions.ErrorMessages;
import com.bamcoreport.web.api.identity.helpers.FileStorageProperties;
import com.bamcoreport.web.api.identity.helpers.HelpUpdate;
import com.bamcoreport.web.api.identity.repositories.RejetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class RejetService implements IRejetService{

//    static  final org.apache.log4j.Logger log4j = org.apache.log4j.Logger.getLogger(RejetService.class.getName());


    @Autowired
    RejetRepository rejetRepository;

//    @Autowired
//    RejetParUtilisateurRepository rejetParUtilisateurRepository;

    @Autowired
    IMapClassWithDto<Rejet, RejetDto> rejetMapping;
//////////////////////////////////////////////////
    private final Path fileStorageLocation;

    @Autowired
    public RejetService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            new Exception("Could not create the directory where the uploaded files will be stored");
        }
    }

    //////////////////////////////////////

    //---- Get all rejets  : --------------------------------------------------------------------


    @Override
    public List<RejetDto> getAllRejets() {
        List<Rejet> rejets = rejetRepository.findAll();
        return rejetMapping.convertListToListDto(rejets, RejetDto.class);
    }

    //---------------------------------------------------------------------------------------------


    //---- Ajouter un rejet : --------------------------------------------------------------------

    @Override
    public RejetDto addRejet(RejetDto rejet) {
        Rejet rejetEntity=rejetMapping.convertToEntity(rejet,Rejet.class);
        rejetEntity=rejetRepository.save(rejetEntity);
        return rejetMapping.convertToDto(rejetEntity, RejetDto.class);
    }



    @Override
    public RejetDto getById(long id) throws Exception {
        Rejet rejetDto = rejetRepository.findById(id).orElse(null);
        if(rejetDto==null) throw new Exception("ErrorMessages.NO_RECORD_FOUND.getErrorMessage()");

        return rejetMapping.convertToDto(rejetDto, RejetDto.class);
    }



    //---------------------------------------------------------------------------------------------

//----------------- delete rejet  : ---------------------------------------------------------

    @Override
    public boolean deleteRejet(long id) {
        try {
            rejetRepository.deleteById(id);
        }catch (Exception ex){
            new Exception(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
            return false;
        }
        return true;
    }


    //--------------------------------------------------------------------------------------------
//--------- Update rejet : -------------------------------------------------------------------
    @Override
    public RejetDto updateRejet(RejetDto rejet) throws Exception {
        Rejet existingRejet =rejetRepository.findById(rejet.getId()).orElse(null);
        if(existingRejet == null) throw new Exception(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        RejetDto saved = rejetMapping.convertToDto(existingRejet,RejetDto.class); // DB
//        UserDto saved = userMapping.convertToDto(userRepository.findById(user.getId()),UserDto.class); // DB
        HelpUpdate.copyNonNullProperties(rejet,saved);
        Rejet rejetEntity=rejetMapping.convertToEntity(saved,Rejet.class);
        rejetEntity=rejetRepository.save(rejetEntity);
        return rejetMapping.convertToDto(rejetEntity,RejetDto.class);
    }

    //--------- Update rejet : -------------------------------------------------------------------

    @Override
    public String storeFile(MultipartFile file) throws IOException {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

//        try {
//            // Check if the file's name contains invalid characters
//            if(fileName.contains("..")) {
//                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
//            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;

    }
    //--------------------------------------------------------------------------------------------
    //--------- Nombre des rejets total : -------------------------------------------------------------------

    @Override
    public int totalRejet() throws IOException {

        int Nombre_Total= (int) rejetRepository.findAll().stream().count();
        return Nombre_Total;
    }

    //--------------------------------------------------------------------------------------------


    //--------- Nombre des rejets par utilisateur : -------------------------------------------------------------------

    @Override
    public List<RejetParUtilisateurDto> rejetPuser()   {

        List<RejetParUtilisateurDto> rejetParUser =rejetRepository.nobreparuser();

        return rejetParUser;
    }

    //--------------------------------------------------------------------------------------------









}
