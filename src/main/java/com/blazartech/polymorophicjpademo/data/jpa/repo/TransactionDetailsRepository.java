/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.blazartech.polymorophicjpademo.data.jpa.repo;

import com.blazartech.polymorophicjpademo.data.jpa.TransactionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author aar1069
 */
@RepositoryRestResource(collectionResourceRel = "transactionDetails", path = "transactionDetails-hateos")
public interface TransactionDetailsRepository extends JpaRepository<TransactionDetails, Long> {
    
}
