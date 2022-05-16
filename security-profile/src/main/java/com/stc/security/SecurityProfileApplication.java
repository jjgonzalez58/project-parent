package com.stc.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * User: job
 * Date: 15/05/22
 * Time: 19:46
 *
 * @author job
 */
@SpringBootApplication
@EnableEurekaClient
public class SecurityProfileApplication {
    public static void main(String[] args){
        SpringApplication.run(SecurityProfileApplication.class,args);
    }
}
