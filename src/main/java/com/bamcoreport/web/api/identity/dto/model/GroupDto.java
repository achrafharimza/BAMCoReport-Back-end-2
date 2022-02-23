package com.bamcoreport.web.api.identity.dto.model;


import com.bamcoreport.web.api.identity.entities.User;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@ApiModel("Group")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupDto {

    private long id;
    private String name;
    private String parentPath;
    private String displayName;
    private String description;
    private User createdBy;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdate;


}
