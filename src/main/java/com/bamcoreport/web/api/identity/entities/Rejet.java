package com.bamcoreport.web.api.identity.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "rejet")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Rejet implements Serializable {


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "flowType")
    private String flowType;

    @Column(name = "rejectNature")
    private String rejectNature;

    @Column(name = "entity")
    private String entity;

    @CreationTimestamp
    private LocalDateTime declarationDate;

    @Column(name = "agencyCode")
    private Long agencyCode;

    @Column(name = "userRegistrationNumber")
    private Long userRegistrationNumber;

    @Column(name = "bu")
    private String bu;

    @Column(name = "su")
    private String su;

    @Column(name = "regionalDelegation")
    private String regionalDelegation;

    @Column(name = "subDelegationType")
    private String subDelegationType;

    @Column(name = "subDelegationName")
    private String subDelegationName;

    @Column(name = "cliFileCode")
    private String cliFileCode;

    @Column(name = "clientCode")
    private String clientCode;

    @Column(name = "Rib")
    private String Rib;

    @Column(name = "gravity")
    private Long gravity;

    @Column(name = "zoneCode")
    private Long zoneCode;

    @Column(name = "isWrongField")
    private Boolean isWrongField;

    @Column(name = "errorCode")
    private Long errorCode;

    @Column(name = "errorLabel")
    private String errorLabel;

    @Column(name = "isRequestTaken")
    private Boolean isRequestTaken;

    @Column(name = "actionDetail")
    private String actionDetail;

    @Column(name = "file")
    private String file;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TakenBy")
    private User TakenBy;





}
