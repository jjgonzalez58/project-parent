package com.stc.discovery;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * User: job
 * Date: 15/05/22
 * Time: 19:16
 *
 * @author job
 */
@EnableDiscoveryClient
@EnableAdminServer
@EnableEurekaServer
@EnableScheduling
@SpringBootApplication
public class DiscoveryServiceApplication {
    public static void main(String[] args){
        SpringApplication.run(DiscoveryServiceApplication.class,args);
    }
}
