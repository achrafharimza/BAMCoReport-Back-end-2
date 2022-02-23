package com.bamcoreport.web.api.identity.dto.model;

import com.bamcoreport.web.api.identity.entities.User;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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


}
