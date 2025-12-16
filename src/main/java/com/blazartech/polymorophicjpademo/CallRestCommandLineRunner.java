/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.polymorophicjpademo;

import com.blazartech.polymorophicjpademo.data.jpa.Transaction;
import com.blazartech.polymorophicjpademo.data.jpa.repo.TransactionRepository;
import com.blazartech.polymorophicjpademo.rest.PostMappingResponse;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

/**
 *
 * @author aar1069
 */
@Component
@Order(6)
@Slf4j
public class CallRestCommandLineRunner implements CommandLineRunner {

    @Autowired
    private TransactionRepository transRepo;
    
    private final RestClient restClient = RestClient.create();
    
    @Value("${server.port}")
    private int port;
    
    private String makeUrl() {
        return "http://localhost:" + Integer.toString(port) + "/transaction";
    }
    
    private PostMappingResponse callRest(Transaction t) {
        log.info("making REST call");
        return restClient.post()
                .uri(makeUrl())
                .body(t)
                .retrieve()
                .body(PostMappingResponse.class);
    }
    
    @Override
    public void run(String... args) throws Exception {
        log.info("calling rest for all our transactions");

        List<Transaction> transactions = transRepo.findAll();
        transactions.stream()
                .map(t -> callRest(t))
                .forEach((r -> log.info("got response {}", r)));
    }
    
}
