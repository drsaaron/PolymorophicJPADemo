/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.polymorophicjpademo;

import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javax.sql.DataSource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author aar1069
 */
@Component
@Order(5)
@Slf4j
public class DumpRawDataCommandLineRunner implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void setJdbcTemplate() {
        jdbcTemplate = new JdbcTemplate(dataSource);

    }

    @Data
    private static class TransactionView {
        private long id;
        private LocalDate effectiveDate;
        private BigDecimal amount;
    }
    
    private TransactionView mapTransactionView(ResultSet rs, int rowCount) throws SQLException {
        TransactionView view = new TransactionView();
        view.setAmount(rs.getBigDecimal("TransAmt"));
        view.setEffectiveDate(LocalDate.parse(rs.getString("TransEffDte")));
        view.setId(rs.getLong("TransId"));
        return view;
    }
    
    @Data
    private static class TransactionDetailsView {
        private Long id;
        private int detailType;
        private Long transactionId;
    }
    
    private TransactionDetailsView mapTransactionDetailsView(ResultSet rs, int rowCount) throws SQLException {
        TransactionDetailsView view = new TransactionDetailsView();
        view.setDetailType(rs.getInt("DtlTypCde"));
        view.setId(rs.getLong("TransDtlId"));
        view.setTransactionId(rs.getLong("TransId"));
        return view;
    }
    
    @Data
    private static class TransactionDetailsType1View {
        private Long id;
        private String clientName;
        private BigDecimal generatingAmount;
    }
    
    private TransactionDetailsType1View mapTransactionDetailType1View(ResultSet rs, int rowCount) throws SQLException {
        TransactionDetailsType1View view = new TransactionDetailsType1View();
        view.setClientName(rs.getString("ClntNam"));
        view.setGeneratingAmount(rs.getBigDecimal("GenAmt"));
        view.setId(rs.getLong("TransDtlId"));
        return view;
    }
    
    @Data 
    private static class TransactionDetailsType2View {
        private Long id;
        private BigDecimal rate;
        private String rateType;
    }
    
    private TransactionDetailsType2View mapTransactionDetailsType2View(ResultSet rs, int rowCount) throws SQLException {
        TransactionDetailsType2View view = new TransactionDetailsType2View();
        view.setRate(rs.getBigDecimal("Rate"));
        view.setRateType(rs.getString("RateTypCde"));
        view.setId(rs.getLong("TransDtlId"));
        return view;
    }
    
    @Override
    public void run(String... args) throws Exception {
        log.info("dumping raw data");
        
        List<TransactionView> transactions = jdbcTemplate.query("select * from Transaction", (rs, rc) -> mapTransactionView(rs, rc));
        transactions.forEach(t -> log.info("found transaction {}", t));
        
        log.info("");
        
        List<TransactionDetailsView> transactionDetails = jdbcTemplate.query("select * from TransDtl", (rs, rc) -> mapTransactionDetailsView(rs, rc));
        transactionDetails.forEach(d -> log.info("found transaction detail {}", d));
        
        log.info("");
        
        List<TransactionDetailsType1View> type1DetailsView = jdbcTemplate.query("select * from TransDtlTyp1", (rs, rc) -> mapTransactionDetailType1View(rs, rc));
        type1DetailsView.forEach(d -> log.info("found detail type 1: {}", d));
        
        log.info("");
        
        List<TransactionDetailsType2View> type2DetailsView = jdbcTemplate.query("select * from TransDtlTyp2", (rs, rc) -> mapTransactionDetailsType2View(rs, rc));
        type2DetailsView.forEach(d -> log.info("found detail type 2: {}", d));
    }
}
