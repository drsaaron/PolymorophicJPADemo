/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.blazartech.polymorophicjpademo.data.jpa.repo;

import com.blazartech.polymorophicjpademo.config.DataSourceConfiguration;
import com.blazartech.polymorophicjpademo.config.EntityManagerConfiguration;
import com.blazartech.polymorophicjpademo.config.JpaVendorAdapterConfiguration;
import com.blazartech.polymorophicjpademo.config.TransactionManagerConfiguration;
import com.blazartech.polymorophicjpademo.data.jpa.TransactionDetails;
import com.blazartech.polymorophicjpademo.data.jpa.TransactionDetailsType1;
import com.blazartech.polymorophicjpademo.data.jpa.TransactionDetailsType2;
import jakarta.transaction.Transactional;
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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author aar1069
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
    TransactionDetailsRepositoryTest.TransactionDetailsRepositoryTestConfiguration.class,
    DataSourceConfiguration.class,
    EntityManagerConfiguration.class,
    JpaVendorAdapterConfiguration.class,
    TransactionManagerConfiguration.class
})
@Slf4j
@Transactional
public class TransactionDetailsRepositoryTest {
    
    @Configuration
    @PropertySource("classpath:unittest.properties")
    @EnableJpaRepositories(basePackages = "com.blazartech.polymorophicjpademo.data.jpa.repo")
    public static final class TransactionDetailsRepositoryTestConfiguration {
        
    }
    
    @Autowired
    private TransactionDetailsRepository instance;
    
    public TransactionDetailsRepositoryTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of findByDetailType method, of class TransactionRepository.
     */
    @Test
    @Sql("/repoTest.sql")
    public void testFindByDetailType() {
        log.info("findByDetailType");
        
        int detailType = 1;
        Collection<TransactionDetails> result = instance.findByDetailType(detailType);
        
        assertNotNull(result);
        assertTrue(result.size() == 1);
        
        TransactionDetails firstTrans = result.iterator().next();
        assertTrue(firstTrans instanceof TransactionDetailsType1);
    }
    
    @Test
    @Sql("/repoTest.sql")
    public void testFindByDetailType_notFound() {
        log.info("findByDetailType_notFound");
        
        int detailType = 3;
        Collection<TransactionDetails> result = instance.findByDetailType(detailType);
        
        assertNotNull(result);
        assertTrue(result.isEmpty());

    }
    
    // unit test to verify the data is saved correctly in the setUp
    @Test
    @Sql("/repoTest.sql")
    public void testAddAll() {
        log.info("add all");
        
        Iterable<TransactionDetails> result = instance.findAll();
        List<TransactionDetails> transactions = new ArrayList<>();
        result.forEach(transactions::add);
        
        assertEquals(2, transactions.size());
        assertTrue(transactions.get(0) instanceof TransactionDetailsType1);
        assertTrue(transactions.get(1) instanceof TransactionDetailsType2);
    }
    
}
