/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.polymorophicjpademo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 *
 * @author AAR1069
 */
@Configuration
public class JpaVendorAdapterConfiguration {
    
    @Bean
    public JpaVendorAdapter jpaVendorAdaptor() {
        HibernateJpaVendorAdapter va = new HibernateJpaVendorAdapter();
        va.setShowSql(true);
        va.setGenerateDdl(true);
        return va;
    }
}
