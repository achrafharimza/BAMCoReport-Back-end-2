package com.bamcoreport.web.api.identity.repositories;

import com.bamcoreport.web.api.identity.entities.ProfileMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileMemberRepository extends JpaRepository<ProfileMember,Long> {
}
