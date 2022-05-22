package com.stc.security.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * User: job
 * Date: 19/05/22
 * Time: 19:19
 *
 * @author job
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.stc.security.repository",
        entityManagerFactoryRef = "entityManagerFactoryMysql",
        transactionManagerRef = "transactionMangerMysql"
)
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "datasource.mysql.jpa")
@Data
@Slf4j
public class MysqlJpaconf {
    private String dialect;
    private String showSql;

    private  final DataSource dataSource;
    @Autowired
    public MysqlJpaconf(@Qualifier("mysqlDatasource") DataSource dataSource){
        this.dataSource = dataSource;
    }
    @Bean("JpaVendorAdapter")
    public JpaVendorAdapter builVendorAdapter(){
        HibernateJpaVendorAdapter vendor = new HibernateJpaVendorAdapter();
        vendor.setDatabase(Database.MYSQL);
        vendor.setDatabasePlatform(this.dialect);
        vendor.setShowSql(Boolean.parseBoolean(this.showSql));
        vendor.setGenerateDdl(false);
        return vendor;
    }
    @Bean("entityManagerFactoryMysql")
    public EntityManagerFactory buildEntityMangerFactory(){
        LocalContainerEntityManagerFactoryBean localContainer = new LocalContainerEntityManagerFactoryBean();
        localContainer.setDataSource(this.dataSource);
        localContainer.setPersistenceUnitName("EMMysql");
        localContainer.setPackagesToScan("com.stc.security.model");
        localContainer.setJpaVendorAdapter(builVendorAdapter());
        localContainer.afterPropertiesSet();
        return localContainer.getObject();
    }
    @Bean("transactionMangerMysql")
    public JpaTransactionManager buildTransactionManger(){
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(buildEntityMangerFactory());
        return jpaTransactionManager;
    }

    @Bean("persistenceExceptionTraslationPostProcessor")
    public PersistenceExceptionTranslationPostProcessor buildPersistenceExceptionTraslationPostProcessor(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
