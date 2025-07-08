/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.polymorophicjpademo;

import com.blazartech.polymorophicjpademo.data.jpa.Transaction;
import com.blazartech.polymorophicjpademo.data.jpa.repo.TransactionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.stream.StreamSupport;
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
@Order(4)
@Slf4j
public class JsonifyTransactionsCommandLineRunner implements CommandLineRunner {

    @Autowired
    private TransactionRepository transRepo;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    private String toJson(Transaction t) {
        try {
            return objectMapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("error json-ifying object: " + e.getMessage(), e);
        }
    }

    private Transaction fromJson(String json) {
        try {
            return objectMapper.readValue(json, Transaction.class);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException("error reading from json: " + ex.getMessage(), ex);
        }
    }
    
    @Override
    public void run(String... args) throws Exception {
        log.info("json-ifying the data");
        
        Iterable<Transaction> transactions = transRepo.findAll();
        StreamSupport.stream(transactions.spliterator(), false)
                .map(t -> toJson(t))
                .peek(json -> log.info("json-ified transaction {}", json))
                .map(json -> fromJson(json))
                .forEach(t -> log.info("de-json-ified transaction {}", t));
    }
    
}
