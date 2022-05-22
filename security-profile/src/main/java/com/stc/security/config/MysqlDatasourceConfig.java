package com.stc.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * User: job
 * Date: 19/05/22
 * Time: 19:15
 *
 * @author job
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "datasource.mysql")
@Data
public class MysqlDatasourceConfig {
    private String url;
    private String username;
    private String password;
    private String driverClassName;

    @Bean(name = "mysqlDatasource")
    public DataSource builMysqlDatasource(){
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(this.driverClassName);
        driverManagerDataSource.setUrl(this.url);
        driverManagerDataSource.setUsername(this.username);
        driverManagerDataSource.setPassword(this.password);
        return driverManagerDataSource;
    }
}
