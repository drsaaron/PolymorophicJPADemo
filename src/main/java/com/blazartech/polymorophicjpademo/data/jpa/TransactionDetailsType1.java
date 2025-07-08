/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.polymorophicjpademo.data.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * one type of detail
 * 
 * @author aar1069
 */
@Entity
@Table(name = "TransDtlTyp1")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class TransactionDetailsType1 extends TransactionDetails {
    
    @Column(name = "ClntNam")
    private String clientName;
    
    @Column(name = "GenAmt")
    private BigDecimal generatingAmount;

    public TransactionDetailsType1() {
        setDetailType(1);
    }
    
    
}
