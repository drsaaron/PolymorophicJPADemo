/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.polymorophicjpademo.websiteuser;

import com.blazartech.polymorophicjpademo.websiteuser.data.jpa.WebsiteUser;
import com.blazartech.polymorophicjpademo.websiteuser.data.jpa.repo.WebsiteUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author aar1069
 */
@Component
@Slf4j
public class InitializeWebsiteUsersCommandLineRunner implements CommandLineRunner {

    @Autowired
    private WebsiteUserRepository repo;
    
    @Override
    public void run(String... args) throws Exception {
        log.info("adding initial user");
        
        WebsiteUser u = new WebsiteUser(null, "test user", "me@you.com");
        WebsiteUser u2 = new WebsiteUser(null, "excellent user", "aaa@top.com");
        repo.save(u);
        repo.save(u2);
    }
    
}
