package com.bamcoreport.web.api.identity.services;

import com.bamcoreport.web.api.identity.dto.model.RejetDto;
import com.bamcoreport.web.api.identity.dto.model.RoleDto;
import com.bamcoreport.web.api.identity.dto.model.UserDto;
import com.bamcoreport.web.api.identity.dto.services.IMapClassWithDto;
import com.bamcoreport.web.api.identity.entities.Rejet;
import com.bamcoreport.web.api.identity.entities.Role;
import com.bamcoreport.web.api.identity.entities.User;
import com.bamcoreport.web.api.identity.exceptions.ErrorMessages;
import com.bamcoreport.web.api.identity.helpers.HelpUpdate;
import com.bamcoreport.web.api.identity.repositories.RejetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RejetService implements IRejetService{

//    static  final org.apache.log4j.Logger log4j = org.apache.log4j.Logger.getLogger(RejetService.class.getName());


    @Autowired
    RejetRepository rejetRepository;

    @Autowired
    IMapClassWithDto<Rejet, RejetDto> rejetMapping;


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



    //--------------------------------------------------------------------------------------------
}
