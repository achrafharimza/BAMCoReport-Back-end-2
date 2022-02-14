package com.bamcoreport.web.api.identity.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "usercontactinfo")
@ToString

public class UserContactInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Nullable
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User userId;

    @Column(name = "email")
    private String email;

    @Column(name = "phone", length = 50)
    private String phone;

    @Column(name = "mobile", length = 50)
    private String mobile;

    @Column(name = "fax", length = 50)
    private String fax;

    @Column(name = "building", length = 50)
    private String building;

    @Column(name = "room", length = 50)
    private String room;

    @Column(name = "address")
    private String address;

    @Column(name = "zipcode", length = 50)
    private String zipCode;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "website")
    private String website;

    @Column(name = "personal", columnDefinition = "boolean default false")
    private Boolean personal;




}
