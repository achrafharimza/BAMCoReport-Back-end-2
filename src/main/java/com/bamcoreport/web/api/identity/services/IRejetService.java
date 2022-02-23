package com.bamcoreport.web.api.identity.services;

import com.bamcoreport.web.api.identity.dto.model.RejetDto;
import com.bamcoreport.web.api.identity.dto.model.RejetParUtilisateurDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IRejetService {
    List<RejetDto> getAllRejets();
    RejetDto addRejet(RejetDto rejet);
    String storeFile(MultipartFile file) throws IOException;
    RejetDto getById(long id) throws Exception;
    boolean deleteRejet(long id);
    RejetDto updateRejet(RejetDto rejet) throws Exception;
    int totalRejet() throws IOException;
    List<RejetParUtilisateurDto> rejetPuser() ;

}
