/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.polymorophicjpademo.data.jpa;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

/**
 *
 * @author aar1069
 */
@Entity
@Table(name = "Transaction")
@Data
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TransId")
    private Long transId;
    
    @Column(name = "TransEffDte")
    private LocalDate effectiveDate;
    
    @Column(name = "TransAmt")
    private BigDecimal amount;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TransId", referencedColumnName = "TransId")
    private TransactionDetails details;
 
}
