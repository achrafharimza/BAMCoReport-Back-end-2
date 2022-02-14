package com.bamcoreport.web.api.identity.services;

import com.bamcoreport.web.api.identity.dto.model.RejetDto;
import com.bamcoreport.web.api.identity.dto.model.RoleDto;
import com.bamcoreport.web.api.identity.dto.model.UserDto;

import java.util.List;

public interface IRejetService {
    List<RejetDto> getAllRejets();
    RejetDto addRejet(RejetDto rejet);
    RejetDto getById(long id) throws Exception;
    boolean deleteRejet(long id);
    RejetDto updateRejet(RejetDto rejet) throws Exception;

}
