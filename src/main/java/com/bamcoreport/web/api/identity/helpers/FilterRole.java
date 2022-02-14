package com.bamcoreport.web.api.identity.helpers;

import com.bamcoreport.web.api.identity.config.SecurityConstants;
import com.bamcoreport.web.api.identity.entities.User;
import com.bamcoreport.web.api.identity.repositories.RoleRepository;
import com.bamcoreport.web.api.identity.repositories.UserMembershipRepository;
import com.bamcoreport.web.api.identity.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

public class FilterRole {

    @Autowired
   private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public String filter ( String tokenb){
        System.out.println("\n");
        System.out.println("tokenb");
        System.out.println("\n");
        System.out.println(tokenb);

             String  token = tokenb.replace(SecurityConstants.TOKEN_PREFIX, "");

        System.out.println("\n");
        System.out.println("token");
        System.out.println(token);

            String username = Jwts.parser()
                    .setSigningKey( SecurityConstants.TOKEN_SECRET )
                    .parseClaimsJws( token )
                    .getBody()
                    .getSubject();

        System.out.println("\n");
        System.out.println("username");
        System.out.println(username);
            if (username != null) {
                System.out.println("\n");
                System.out.println("inif");
                System.out.println(username);
                User existingUser = userRepository.findByUsername(username);
                System.out.println("\n");
                System.out.println("userEntity");
                System.out.println(existingUser.getUsername());


                if (existingUser != null) {
                    long id =existingUser.getId();

                 String role=roleRepository.findrole(id);
              return role;

                }
            }


return "not definded";







}








}
