/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.blazartech.polymorophicjpademo.data.jpa.repo;

import com.blazartech.polymorophicjpademo.config.DataSourceConfiguration;
import com.blazartech.polymorophicjpademo.config.EntityManagerConfiguration;
import com.blazartech.polymorophicjpademo.config.JpaVendorAdapterConfiguration;
import com.blazartech.polymorophicjpademo.config.TransactionManagerConfiguration;
import com.blazartech.polymorophicjpademo.data.jpa.Transaction;
import com.blazartech.polymorophicjpademo.data.jpa.TransactionDetailsType1;
import com.blazartech.polymorophicjpademo.data.jpa.TransactionDetailsType2;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author aar1069
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
    TransactionRepositoryTest.TransactionRepositoryTestConfiguration.class,
    DataSourceConfiguration.class,
    EntityManagerConfiguration.class,
    JpaVendorAdapterConfiguration.class,
    TransactionManagerConfiguration.class
})
@Slf4j
@Transactional
public class TransactionRepositoryTest {
    
    @Configuration
    @PropertySource("classpath:unittest.properties")
    @EnableJpaRepositories(basePackages = "com.blazartech.polymorophicjpademo.data.jpa.repo")
    public static final class TransactionRepositoryTestConfiguration {
        
    }
    
    @Autowired
    private TransactionRepository instance;
    
    public TransactionRepositoryTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
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
        instance.saveAll(transactions);
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of findByDetailType method, of class TransactionRepository.
     */
    @Test
    public void testFindByDetailType() {
        log.info("findByDetailType");
        
        int detailType = 1;
        Collection<Transaction> result = instance.findByDetailType(detailType);
        
        assertNotNull(result);
        assertTrue(result.size() == 1);
        
        Transaction firstTrans = result.iterator().next();
        assertTrue(firstTrans.getDetails() instanceof TransactionDetailsType1);
    }
    
    @Test
    public void testFindByDetailType_notFound() {
        log.info("findByDetailType_notFound");
        
        int detailType = 3;
        Collection<Transaction> result = instance.findByDetailType(detailType);
        
        assertNotNull(result);
        assertTrue(result.isEmpty());

    }
    
    // unit test to verify the data is saved correctly in the setUp
    @Test
    public void testAddAll() {
        log.info("add all");
        
        Iterable<Transaction> result = instance.findAll();
        List<Transaction> transactions = new ArrayList<>();
        result.forEach(transactions::add);
        
        assertEquals(2, transactions.size());
        assertTrue(transactions.get(0).getDetails() instanceof TransactionDetailsType1);
        assertTrue(transactions.get(1).getDetails() instanceof TransactionDetailsType2);
    }
}
