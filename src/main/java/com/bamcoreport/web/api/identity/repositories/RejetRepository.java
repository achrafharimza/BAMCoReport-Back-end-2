package com.bamcoreport.web.api.identity.repositories;

import com.bamcoreport.web.api.identity.entities.Rejet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  RejetRepository extends JpaRepository<Rejet,Long> {
}
