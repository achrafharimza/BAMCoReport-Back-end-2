package com.bamcoreport.web.api.identity.services;

import com.bamcoreport.web.api.identity.dto.model.ProfileDto;
import com.bamcoreport.web.api.identity.dto.model.ProfileMemberDto;

import java.util.List;

public interface IProfilMemberService {
    List<ProfileMemberDto> getAllProfilsMembers();
    ProfileMemberDto addProfilMember(ProfileMemberDto profileMemberDto);
    boolean deleteProfilMember(long id);
    ProfileMemberDto updateProfilMember(ProfileMemberDto profileMemberDto);
}
