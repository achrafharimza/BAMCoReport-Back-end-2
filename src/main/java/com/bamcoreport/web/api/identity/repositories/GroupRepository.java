package com.bamcoreport.web.api.identity.repositories;

import com.bamcoreport.web.api.identity.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
}
