/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.polymorophicjpademo.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

/**
 *
 * @author AAR1069
 */
@Configuration
public class EntityManagerConfiguration {

    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private JpaVendorAdapter jpaVendorAdapter;
    
    @Autowired
    private Environment env;
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean f = new LocalContainerEntityManagerFactoryBean();
        f.setDataSource(dataSource);
        f.setPersistenceXmlLocation("classpath:META-INF/persistence.xml");
        f.setJpaVendorAdapter(jpaVendorAdapter);
        f.setPersistenceUnitName("com.blazartech_PolymorphicJPA_jar_1.0-SNAPSHOTPU");
        
        // set the hibernate.hbm2ddl.auto property.  Hibernate desperately wants to do
        // schema changes, which is obviously not right.  I've tried setting this
        // property in the persistence.xml file, but hibernate doesn't seem to pick 
        // it up.  So set it here.  And set it to an invalid date so that it doesn't
        // do a thing.
        Properties props = new Properties();
        props.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        props.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect")); 
        f.setJpaProperties(props);
        
        return f;
    }
}
