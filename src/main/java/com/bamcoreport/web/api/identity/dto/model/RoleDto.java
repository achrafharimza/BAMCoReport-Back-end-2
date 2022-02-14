package com.bamcoreport.web.api.identity.dto.model;

import com.bamcoreport.web.api.identity.entities.ProfileMember;
import com.bamcoreport.web.api.identity.entities.User;
import com.bamcoreport.web.api.identity.entities.UserMembership;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ApiModel("Role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    private long id;
    private String name;
    private String displayName;
    private String description;
    private User createdBy;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdate;
    private List<ProfileMember> profileMembers=new ArrayList<>();
    private List<UserMembership> userMemberships=new ArrayList<>();






}
