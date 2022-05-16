package com.stc.dispatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * User: job
 * Date: 15/05/22
 * Time: 20:03
 *
 * @author job
 */
@SpringBootApplication
@EnableEurekaClient
public class DispatchServiceApplication {
    public static void main(String[] args){
        SpringApplication.run(DispatchServiceApplication.class,args);
    }
}
