package com.bamcoreport.web.api.identity.dto.model;

import com.bamcoreport.web.api.identity.entities.Group;
import com.bamcoreport.web.api.identity.entities.Role;
import com.bamcoreport.web.api.identity.entities.User;
import com.bamcoreport.web.api.identity.entities.UserContactInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
@ApiModel("UserMembership")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMembershipDto {

    private long id;
    private User userId;
    private Role roleId;
    private Group groupId;
    private User assignedBy;
    private LocalDateTime assignedDate;


}
