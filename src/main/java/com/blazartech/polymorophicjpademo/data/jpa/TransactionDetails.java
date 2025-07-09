/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.polymorophicjpademo.data.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

/**
 * Base class for the transaction details.
 * 
 * @author aar1069
 */
@Entity
@Table(name = "TransDtl")
@Inheritance(strategy = InheritanceType.JOINED) // use child tables for the data, other options available
//@DiscriminatorColumn(name = "DtlTypCde", discriminatorType = DiscriminatorType.INTEGER)
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY) // include a type indicator in teh JSON
@JsonSubTypes({ // map type indicators to the appropriate class
    @JsonSubTypes.Type(value = TransactionDetailsType1.class, name = "TransactionDetailsType1"),
    @JsonSubTypes.Type(value = TransactionDetailsType2.class, name = "TransactionDetailsType2")}
)
public abstract class TransactionDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "TransDtlId")
    private Long transactionDetailId;
    
    /**
     * the detail type.  Each type class should set this value in its constructor
     */
    @Column(name = "DtlTypCde")
    @Min(value = 1)
    @Max(value = 2)
    @Setter(AccessLevel.PROTECTED) private int detailType;
    
    @JoinColumn(name = "TransId", referencedColumnName = "TransId")
    @OneToOne(optional = false)
    @NotNull
    @JsonIgnore
    private Transaction transaction;
}
