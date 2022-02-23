package com.bamcoreport.web.api.identity.services;

import com.bamcoreport.web.api.identity.dto.model.ProfileDto;
import com.bamcoreport.web.api.identity.dto.model.ProfileMemberDto;
import com.bamcoreport.web.api.identity.dto.model.RoleDto;
import com.bamcoreport.web.api.identity.dto.services.IMapClassWithDto;
import com.bamcoreport.web.api.identity.entities.Profile;
import com.bamcoreport.web.api.identity.entities.ProfileMember;
import com.bamcoreport.web.api.identity.entities.Role;
import com.bamcoreport.web.api.identity.helpers.HelpUpdate;
import com.bamcoreport.web.api.identity.repositories.ProfileMemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfilMemberService implements IProfilMemberService{

    private static final Logger log = LoggerFactory.getLogger(UserService.class);


    @Autowired
    ProfileMemberRepository profileMemberRepository;

    @Autowired
    IMapClassWithDto<ProfileMember, ProfileMemberDto> profilMemberMapping;


    //---- Get all roles  : --------------------------------------------------------------------

    public List<ProfileMemberDto> getAllProfilsMembers() {
        List<ProfileMember> profileMembers = profileMemberRepository.findAll();
        return  profilMemberMapping.convertListToListDto(profileMembers, ProfileMemberDto.class);
    }

    //------------------------------------------------------------------------------------------


    // ---- Add profil Member ---------------------------------------------------------------------
    @Override
    public ProfileMemberDto addProfilMember(ProfileMemberDto profile) {
        ProfileMember profileMemberEntity=profilMemberMapping.convertToEntity(profile,ProfileMember.class);
        profileMemberEntity = profileMemberRepository.save(profileMemberEntity);
        return profilMemberMapping.convertToDto(profileMemberEntity, ProfileMemberDto.class);
    }

    //----------------------------------------------------------------------------------------------


    //---- delete profil Member --------------------------------------------------------------------

    @Override
    public boolean deleteProfilMember(long id) {
        try {
            profileMemberRepository.deleteById(id);
        }catch (Exception ex){
            return false;
        }
        return true;
    }



    //-----------------------------------------------------------------------------------------------


    @Override
    public ProfileMemberDto updateProfilMember(ProfileMemberDto profile) {
        ProfileMemberDto saved = profilMemberMapping.convertToDto(profileMemberRepository.getById(profile.getId()),ProfileMemberDto.class); // DB
        HelpUpdate.copyNonNullProperties(profile,saved);
        ProfileMember profileMemberEntity=profilMemberMapping.convertToEntity(saved,ProfileMember.class);
        profileMemberEntity = profileMemberRepository.save(profileMemberEntity);
        return profilMemberMapping.convertToDto(profileMemberEntity, ProfileMemberDto.class);
    }

    //---------------------------------------------------------------------------------------------











}
