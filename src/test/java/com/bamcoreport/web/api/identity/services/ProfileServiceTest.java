package com.bamcoreport.web.api.identity.services;

import com.bamcoreport.web.api.identity.dto.model.ProfileDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProfileServiceTest {

    @Autowired
    ProfileService profileservice;

    List<ProfileDto> test = new ArrayList<>();
    ProfileDto prf = new ProfileDto();


    @Test
    void getAllProfils() {
        assertNotEquals(test,profileservice.getAllProfils());

    }

    @Test
    void addProfil() {
        prf.setName("loubna");
        prf.setDescription("ddddddd");
        assertNotEquals(null,profileservice.addProfil(prf));
    }

    @Test
    void deleteProfil() {
    }

    @Test
    void updateProfil() {
        prf.setId(6L);
        prf.setName("loubna");
        prf.setDescription("ddddddd");
        assertNotEquals(null,profileservice.updateProfil(prf));
    }
}