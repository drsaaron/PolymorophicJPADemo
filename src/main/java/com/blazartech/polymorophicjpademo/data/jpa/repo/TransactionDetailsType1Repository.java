/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.blazartech.polymorophicjpademo.data.jpa.repo;

import com.blazartech.polymorophicjpademo.data.jpa.TransactionDetailsType1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 *
 * @author scott
 */
@RepositoryRestResource(collectionResourceRel = "transactionDetailsType1", path = "transactionDetailsType1-hateos")
public interface TransactionDetailsType1Repository extends JpaRepository<TransactionDetailsType1, Long>{
    
}
