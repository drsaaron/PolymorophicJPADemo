/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.blazartech.polymorophicjpademo.websiteuser.data.jpa.repo;

import com.blazartech.polymorophicjpademo.websiteuser.data.jpa.WebsiteUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author aar1069
 */
@RepositoryRestResource(collectionResourceRel = "users", path = "users")
@Tag(description = "a simple HATEOS API for users", name = "website users API")
public interface WebsiteUserRepository extends JpaRepository<WebsiteUser, Long>, PagingAndSortingRepository<WebsiteUser, Long> {
    
    @Operation(summary = "find users by name")
    Collection<WebsiteUser> findByName(String name);
    
}
