/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.polymorophicjpademo.data.jpa;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Data;

/**
 *
 * @author aar1069
 */
@Entity
@Table(name = "TransDtl")
@Inheritance(strategy = InheritanceType.JOINED)
//@DiscriminatorColumn(name = "DtlTypCde", discriminatorType = DiscriminatorType.INTEGER)
@Data
public abstract class TransactionDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TransDtlId")
    private Long transactionDetailId;
    
    @Column(name = "DtlTypCde")
    private int detailType;
    
    @Column(name = "TransId")
    private Long transactionId;
}
