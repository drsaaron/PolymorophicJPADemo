/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.polymorophicjpademo;

import com.blazartech.polymorophicjpademo.data.jpa.Transaction;
import com.blazartech.polymorophicjpademo.data.jpa.TransactionDetailsType1;
import com.blazartech.polymorophicjpademo.data.jpa.TransactionDetailsType2;
import com.blazartech.polymorophicjpademo.data.jpa.repo.TransactionRepository;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
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
@Order(1)
@Slf4j
public class CreateDataCommandLineRunner implements CommandLineRunner {

    @Autowired
    private TransactionRepository transRepo;
    
    @Override
    @Transactional
    public void run(String... args) throws Exception {
        log.info("creating data");
        
        TransactionDetailsType1 detail1 = new TransactionDetailsType1();
        detail1.setClientName("Henrietta Schmoop");
        detail1.setGeneratingAmount(BigDecimal.TEN);
        
        TransactionDetailsType2 detail2 = new TransactionDetailsType2();
        detail2.setRate(BigDecimal.ONE);
        detail2.setRateType("flat");

        Transaction t1 = new Transaction();
        t1.setAmount(new BigDecimal("51.50"));
        t1.setEffectiveDate(LocalDate.parse("2025-06-01"));
        t1.setDetails(detail1);
        
        Transaction t2 = new Transaction();
        t2.setAmount(new BigDecimal("52"));
        t2.setDetails(detail2);
        t2.setEffectiveDate(LocalDate.parse("2025-07-15"));
        
        List<Transaction> transactions = List.of(t1, t2);
        
        // save
        log.info("saving transactions {}", transactions);
        transRepo.saveAll(transactions);
    }
    
}
