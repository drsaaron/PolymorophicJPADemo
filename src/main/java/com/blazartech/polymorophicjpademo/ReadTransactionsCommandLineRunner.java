/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.polymorophicjpademo;

import com.blazartech.polymorophicjpademo.data.jpa.Transaction;
import com.blazartech.polymorophicjpademo.data.jpa.repo.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * read the transactions from the Create command line runner
 * @author aar1069
 */
@Component
@Order(2)
@Slf4j
public class ReadTransactionsCommandLineRunner implements CommandLineRunner {

    @Autowired
    private TransactionRepository transRepo;
    
    @Override
    public void run(String... args) throws Exception {
        log.info("reading all transactions");
        
        Iterable<Transaction> transactions = transRepo.findAll();
        transactions.forEach(t -> log.info("read transaction {}", t));
    }
    
}
