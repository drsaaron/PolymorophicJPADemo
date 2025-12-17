/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.blazartech.polymorophicjpademo.websiteuser.data.jpa.repo;

import com.blazartech.polymorophicjpademo.websiteuser.data.jpa.WebsiteUser;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author aar1069
 */
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface WebsiteUserRepository extends JpaRepository<WebsiteUser, Long>, PagingAndSortingRepository<WebsiteUser, Long> {
    
    Collection<WebsiteUser> findByName(String name);
    
}
