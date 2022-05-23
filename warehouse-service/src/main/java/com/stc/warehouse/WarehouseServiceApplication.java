package com.stc.warehouse;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * User: job
 * Date: 15/05/22
 * Time: 20:08
 *
 * @author job
 */
@SpringBootApplication
@EnableEurekaClient
public class WarehouseServiceApplication {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    public static void main(String[] args){
        SpringApplication.run(WarehouseServiceApplication.class,args);
    }
}
