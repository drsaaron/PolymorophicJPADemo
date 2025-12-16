/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.polymorophicjpademo.rest;

import com.blazartech.polymorophicjpademo.data.jpa.Transaction;
import com.blazartech.polymorophicjpademo.data.jpa.TransactionDetailsType1;
import com.blazartech.polymorophicjpademo.data.jpa.TransactionDetailsType2;
import com.blazartech.polymorophicjpademo.data.jpa.repo.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author aar1069
 */
@RestController
@Slf4j
public class TransactionRestController {
    
    @Autowired
    private TransactionRepository repo;
    
    @PostMapping(path = "/transaction")
    public PostMappingResponse postTransaction(@RequestBody Transaction transaction) {
        log.info("recieved a post for transaction {}", transaction);

        // handle the circular references in the object.
        if (transaction.getDetails().getTransaction() == null) {
            transaction.getDetails().setTransaction(transaction);
        }
        
        switch (transaction.getDetails()) {
            case TransactionDetailsType1 transactionDetailsType1 -> log.info("this contains a detail of type 1 with client name {}", transactionDetailsType1.getClientName());
            case TransactionDetailsType2 transactionDetailsType2 -> log.info("this contains a detail of type 2 with rate type {}", transactionDetailsType2.getRateType());
            default -> {
            }
        }
        
        repo.save(transaction);
        
        return new PostMappingResponse(transaction.getDetails().getClass().toString());
    }
}
