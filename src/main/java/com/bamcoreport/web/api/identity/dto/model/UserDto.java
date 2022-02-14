package com.bamcoreport.web.api.identity.dto.model;

import com.bamcoreport.web.api.identity.entities.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApiModel("User")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {


    private long id;
    private UserContactInfo userContactInfo;
    private Boolean enabled;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String title;
    private String jobTitle;
    private User managerUserId;
    private User createdBy;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdate;
    private String newpassword;
    private List<Role> roles=new ArrayList<>();
    private List<Group> groups=new ArrayList<>();
    private List<ProfileMember> profileMembers=new ArrayList<>();
    private List<User> managerUsers=new ArrayList<>();
    private List<User> createdUsers=new ArrayList<>();
    private List<UserMembership> userMemberships=new ArrayList<>();
    private List<UserMembership> assignebyUserMembership=new ArrayList<>();
    private List<Profile> profile=new ArrayList<>();
    private List<Profile> lastupdate=new ArrayList<>();
    private List<Rejet> TakenBy=new ArrayList<>();




}
