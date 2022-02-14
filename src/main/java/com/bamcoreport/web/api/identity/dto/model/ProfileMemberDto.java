package com.bamcoreport.web.api.identity.dto.model;

import com.bamcoreport.web.api.identity.entities.Group;
import com.bamcoreport.web.api.identity.entities.Profile;
import com.bamcoreport.web.api.identity.entities.Role;
import com.bamcoreport.web.api.identity.entities.User;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("Profilmember")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProfileMemberDto {

    private long id;
    private Profile profileId;
    private User userId;
    private Group groupId;
    private Role roleId;

}
