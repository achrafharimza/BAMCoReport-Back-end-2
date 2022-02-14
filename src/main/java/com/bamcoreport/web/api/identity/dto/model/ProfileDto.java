package com.bamcoreport.web.api.identity.dto.model;

import com.bamcoreport.web.api.identity.entities.Profile;
import com.bamcoreport.web.api.identity.entities.ProfileMember;
import com.bamcoreport.web.api.identity.entities.Role;
import com.bamcoreport.web.api.identity.entities.User;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ApiModel("Profil")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProfileDto {
    private long id;
    private Boolean isDefault;
    private String name;
    private String description;
    private LocalDateTime creationDate;
    private User createdBy;
    private LocalDateTime lastUpdateDate;
    private User lastUpdateBy;
    private List<ProfileMember> profileMembers=new ArrayList<>();



}
