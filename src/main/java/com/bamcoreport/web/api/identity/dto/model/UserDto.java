package com.bamcoreport.web.api.identity.dto.model;

import com.bamcoreport.web.api.identity.entities.User;
import com.bamcoreport.web.api.identity.entities.UserContactInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.Optional;

@ApiModel("User")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {


    private long id;
    private UserContactInfo userContactInfo;
    private Boolean enabled;
    @NotBlank(message="Ce champ ne doit etre null !")
    private String username;
    @NotBlank
    @Size(min=8, message="mot de passe doit avoir au moins 8 caracteres !")
    private String password;
    private String newpassword;
    private String firstname;
    private String lastname;
    @NotNull
    @Email
    private String title;
    private String jobTitle;
    private User managerUserId;
    private User createdBy;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdate;

}
