package com.bamcoreport.web.api.identity.services;

import com.bamcoreport.web.api.identity.dto.model.GroupDto;
import com.bamcoreport.web.api.identity.dto.model.ProfileDto;
import com.bamcoreport.web.api.identity.dto.model.RoleDto;
import com.bamcoreport.web.api.identity.dto.model.UserDto;
import com.bamcoreport.web.api.identity.dto.services.IMapClassWithDto;
import com.bamcoreport.web.api.identity.entities.Profile;
import com.bamcoreport.web.api.identity.entities.Role;
import com.bamcoreport.web.api.identity.helpers.HelpUpdate;
import com.bamcoreport.web.api.identity.repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService implements IProfileService{
    static  final org.apache.log4j.Logger log4j = org.apache.log4j.Logger.getLogger(ProfileService.class.getName());


    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    IMapClassWithDto<Profile, ProfileDto> profileMapping;


    //---- Get all profils : -----------------------------------------------------------------------
    @Override
    public List<ProfileDto> getAllProfils() {
        List<Profile> profiles = profileRepository.findAll();
        return profileMapping.convertListToListDto(profiles, ProfileDto.class);
    }

    //------------------------------------------------------------------------------------------------


    //----  Add profil -------------------------------------------------------------------------------

    @Override
    public ProfileDto addProfil(ProfileDto profile) {
        Profile profileEntity=profileMapping.convertToEntity(profile,Profile.class);
        profileEntity=profileRepository.save(profileEntity);
        return profileMapping.convertToDto(profileEntity, ProfileDto.class);
    }


    //---------------------------------------------------------------------------------------------------



    //----- supprimer profil ---------------------------------------------------------------------------

    @Override
    public boolean deleteProfil(long id) {
        try {
           profileRepository.deleteById(id);
        }catch (Exception ex){
            log4j.error(ex.getMessage());
            return false;
        }
        return true;
    }

    //--------------------------------------------------------------------------------------------------



   //--- Update profil ----------------------------------------------------------------------------------


    @Override
    public ProfileDto updateProfil(ProfileDto profile) {
        ProfileDto saved = profileMapping.convertToDto(profileRepository.getById(profile.getId()),ProfileDto.class); // DB
        HelpUpdate.copyNonNullProperties(profile,saved);
        Profile profileEntity=profileMapping.convertToEntity(saved,Profile.class);
        profileEntity=profileRepository.save(profileEntity);
        return profileMapping.convertToDto(profileEntity, ProfileDto.class);
    }
}

    //--------------------------------------------------------------------------------------------------

