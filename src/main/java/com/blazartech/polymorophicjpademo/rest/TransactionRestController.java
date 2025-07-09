/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.polymorophicjpademo.rest;

import com.blazartech.polymorophicjpademo.data.jpa.Transaction;
import lombok.extern.slf4j.Slf4j;
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
    
    @PostMapping(path = "/transaction")
    public PostMappingResponse postTransaction(@RequestBody Transaction transaction) {
        log.info("recieved a post for transaction {}", transaction);
        return new PostMappingResponse(transaction.getDetails().getClass().toString());
    }
}
