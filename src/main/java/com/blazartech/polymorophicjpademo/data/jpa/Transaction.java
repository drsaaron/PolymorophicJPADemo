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
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

/**
 * The main transaction class.  It will have a few common pieces of data and
 * details derived from the @see{TransactionDetails} class.
 * 
 * @author aar1069
 */
@Entity
@Table(name = "Transaction")
@NamedQueries({
    @NamedQuery(name = "Transaction.findByDetailType", query = "SELECT p FROM Transaction p where p.details.detailType = :detailType")
})
@Data
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TransId")
    private Long transactionId;
    
    @Column(name = "TransEffDte")
    @NotNull
    private LocalDate effectiveDate;
    
    @Column(name = "TransAmt")
    @NotNull
    private BigDecimal amount;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TransId", referencedColumnName = "TransId")
    @NotNull
    private TransactionDetails details;
 
}
