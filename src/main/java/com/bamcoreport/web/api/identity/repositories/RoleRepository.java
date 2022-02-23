package com.bamcoreport.web.api.identity.repositories;

import com.bamcoreport.web.api.identity.entities.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface  RoleRepository extends JpaRepository<Role,Long> {
    @Query(value="select role.name from role,usermembership where userid=:id and usermembership.roleid=role.id", nativeQuery=true)
    String findrole( @Param("id") long id);



}
