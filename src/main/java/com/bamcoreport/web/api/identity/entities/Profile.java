package com.bamcoreport.web.api.identity.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "profile")
@ToString

public class Profile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "isdefault", columnDefinition = "boolean default false")
    private Boolean isDefault;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "description",columnDefinition="TEXT")
    private String description;

    @CreationTimestamp
    @Column(name = "creationdate")
    private LocalDateTime creationDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "createdby")
    private User createdBy;

    @UpdateTimestamp
    @Column(name = "lastupdatedate")
    private LocalDateTime lastUpdateDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lastupdateby")
    private User lastUpdateBy;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "profileId")
    private List<ProfileMember> profileMembers=new ArrayList<>();


}
