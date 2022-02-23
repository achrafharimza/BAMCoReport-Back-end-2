package com.bamcoreport.web.api.identity.repositories;

import com.bamcoreport.web.api.identity.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends PagingAndSortingRepository<User,Long> {
    User findByUsername(String username);
    User findByUsernameAndPassword(String username,String password);


}
