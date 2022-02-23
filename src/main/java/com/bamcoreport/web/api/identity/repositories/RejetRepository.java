package com.bamcoreport.web.api.identity.repositories;

import com.bamcoreport.web.api.identity.dto.model.RejetParUtilisateurDto;
import com.bamcoreport.web.api.identity.entities.Rejet;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.query.spi.NativeQueryImplementor;
import org.hibernate.transform.Transformers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  RejetRepository extends JpaRepository<Rejet,Long> {
    @Query("select new com.bamcoreport.web.api.identity.dto.model.RejetParUtilisateurDto  (u.username ,count(r.TakenBy) ) from Rejet  r,User  u WHERE r.TakenBy.id=u.id GROUP BY u.username")
//    @Query(value="select new com.bamcoreport.web.api.identity.dto.model.RejetParUtilisateurDto  (u.username as utilisateur,count(taken_by) as nombreRejets) from rejet r,users u WHERE r.taken_by=u.id GROUP BY username", nativeQuery=true)
    List<RejetParUtilisateurDto> nobreparuser();



/*
    select CAST(declaration_date AS DATE) as date,count(declaration_date) as nombreRejets from rejet GROUP by CAST(declaration_date AS DATE)
*/



}
