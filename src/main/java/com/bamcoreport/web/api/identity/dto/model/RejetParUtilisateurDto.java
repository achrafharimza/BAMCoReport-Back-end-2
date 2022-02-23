package com.bamcoreport.web.api.identity.dto.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.SqlResultSetMapping;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RejetParUtilisateurDto {



    private String utilisateur;
    private long nombre;

}
