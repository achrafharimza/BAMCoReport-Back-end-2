package com.bamcoreport.web.api.identity.repositories;

import com.bamcoreport.web.api.identity.entities.UserContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserContactInfoRepository extends JpaRepository<UserContactInfo,Long> {


}
