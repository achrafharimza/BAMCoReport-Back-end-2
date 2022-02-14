package com.bamcoreport.web.api.identity.dto.model;

import com.bamcoreport.web.api.identity.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data

public class UserContactInfoDto {

    private long id;
    private User userId;
    private String email;
    private String phone;
    private String mobile;
    private String fax;
    private String building;
    private String room;
    private String address;
    private String zipCode;
    private String city;
    private String state;
    private String country;
    private String website;
    private Boolean personal;

}
