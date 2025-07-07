/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.polymorophicjpademo;

import com.blazartech.polymorophicjpademo.data.jpa.Transaction;
import com.blazartech.polymorophicjpademo.data.jpa.repo.TransactionRepository;
import java.util.Collection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 *
 * @author aar1069
 */
@Component
@Order(3)
@Slf4j
public class QueryTransactionsByDetailTypeCommandLineRunner implements CommandLineRunner {

    @Autowired
    private TransactionRepository transRepo;
    
    @Override
    public void run(String... args) throws Exception {
        int detailType = 1;
        log.info("querying by detail type {}", detailType);
        
        Collection<Transaction> transactions = transRepo.findByDetailType(detailType);
        
        transactions.forEach(t -> log.info("read transaction by type {}: {}", detailType, t));
    }
    
}
