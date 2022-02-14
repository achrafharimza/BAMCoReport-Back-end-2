package com.bamcoreport.web.api.identity.entities;

import com.bamcoreport.web.api.identity.dto.model.UserDto;
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
@Table(name = "groupe")
@ToString
public class Group implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", length = 125)
    private String name;

    @Column(name = "parentpath")
    private String parentPath;

    @Column(name = "displayname")
    private String displayName;

    @Column(name = "description",columnDefinition="TEXT")
    private String description;

    @ManyToOne()
    @JoinColumn(name = "createdby",referencedColumnName = "id",  updatable = false)
    private User createdBy;

    @CreationTimestamp
    @Column(name = "creationdate")
    private LocalDateTime creationDate;

    @UpdateTimestamp
    @Column(name = "lastupdate")
    private LocalDateTime lastUpdate;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "groupId")
    private List<ProfileMember> profileMembers=new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "groupId")
    private List<UserMembership> userMemberships=new ArrayList<>();

}
