package com.bamcoreport.web.api.identity.repositories;

import com.bamcoreport.web.api.identity.entities.UserMembership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMembershipRepository extends JpaRepository<UserMembership,Long> {
}
