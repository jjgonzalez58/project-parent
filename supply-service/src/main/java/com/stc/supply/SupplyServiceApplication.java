package com.stc.supply;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * User: job
 * Date: 15/05/22
 * Time: 19:53
 *
 * @author job
 */
@SpringBootApplication
@EnableEurekaClient
public class SupplyServiceApplication {
    public static void main(String[] args){
        SpringApplication.run(SupplyServiceApplication.class,args);
    }
}
