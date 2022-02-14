package com.bamcoreport.web.api.identity.dto.model;

import com.bamcoreport.web.api.identity.entities.User;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@ApiModel("Rejet")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class RejetDto {
    private Long id;
    private String flowType;
    private String rejectNature;
    private String entity;
    private LocalDateTime declarationDate;
    private Long agencyCode;
    private Long userRegistrationNumber;
    private String bu;
    private String su;
    private String regionalDelegation;
    private String subDelegationType;
    private String subDelegationName;
    private String cliFileCode;
    private String clientCode;
    private String Rib;
    private Long gravity;
    private Long zoneCode;
    private Boolean isWrongField;
    private Long errorCode;
    private String errorLabel;
    private Boolean isRequestTaken;
    private String actionDetail;
    private String file;
    private User TakenBy;
}
