package com.bamcoreport.web.api.identity.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonIgnore
    @OneToOne(targetEntity = UserContactInfo.class, mappedBy = "userId")
    private UserContactInfo userContactInfo;

    @Column(nullable = true, name = "enabled", columnDefinition = "boolean default false")
    private Boolean enabled;

    @Column(name = "username")
    private String username;

    @Column(name = "password", length = 60)
    private String password;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "title", length = 50)
    private String title;

    @Column(name = "jobtitle")
    private String jobTitle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manageruserid")
    private User managerUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "createdby")
    private User createdBy;

    @CreationTimestamp
    @Column(name = "creationdate")
    private LocalDateTime creationDate;

    @UpdateTimestamp
    @Column(name = "lastupdate")
    private LocalDateTime lastUpdate;




}
