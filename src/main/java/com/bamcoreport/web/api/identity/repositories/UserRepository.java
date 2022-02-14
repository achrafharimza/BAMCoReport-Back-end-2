package com.bamcoreport.web.api.identity.repositories;

import com.bamcoreport.web.api.identity.entities.User;
import com.sun.istack.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    User findByUsernameAndPassword(String username,String password);

}
