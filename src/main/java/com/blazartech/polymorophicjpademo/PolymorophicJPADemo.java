/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.blazartech.polymorophicjpademo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

/**
 *
 * @author aar1069
 */
@SpringBootApplication
public class PolymorophicJPADemo {

    public static void main(String[] args) {
        SpringApplication.run(PolymorophicJPADemo.class, args);
    }
    
    @Bean
    public ObjectMapper objectMapper() {
        return JsonMapper.builder()
//                .addModule(new JavaTimeModule())
                .build();
    }
}
