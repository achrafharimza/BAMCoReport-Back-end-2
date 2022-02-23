//package com.bamcoreport.web.api.identity.repositories;
//
//import com.bamcoreport.web.api.identity.dto.model.RejetParUtilisateurDto;
//import com.bamcoreport.web.api.identity.entities.UserContactInfo;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface RejetParUtilisateurRepository extends JpaRepository<RejetParUtilisateurDto,Long> {
//    @Query(value="select u.username as utilisateur,count(taken_by) as nombre_rejets from rejet r,users u WHERE r.taken_by=u.id GROUP BY username", nativeQuery=true)
//    List<RejetParUtilisateurDto> nobreparuser();
//}
