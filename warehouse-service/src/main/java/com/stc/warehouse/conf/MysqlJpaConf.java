package com.stc.warehouse.conf;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
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

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.stc.warehouse.repositories",
        entityManagerFactoryRef = "entityManagerFactoryMysql",
        transactionManagerRef = "transactionManagerMysql"
)
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "datasource.jpa")
@Data
public class MysqlJpaConf {
    private String dialect;
    private String showsql;

    private final DataSource  dataSource;

    @Autowired
    public MysqlJpaConf(DataSource  mysqlDatasource){
        this.dataSource = mysqlDatasource;
    }

    @Bean("entityManagerFactoryMysql")
    public EntityManagerFactory builEntityMangerMysql(){
        LocalContainerEntityManagerFactoryBean  locaContainer = new LocalContainerEntityManagerFactoryBean();
        locaContainer.setDataSource(this.dataSource);
        locaContainer.setPersistenceUnitName("MysqlD");
        locaContainer.setPackagesToScan("com.stc.warehouse.entities");
        locaContainer.setJpaVendorAdapter(builVendorAdapter());
        locaContainer.afterPropertiesSet();
        return locaContainer.getObject();
    }
    @Bean("JpaVendorAdapter")
    public JpaVendorAdapter builVendorAdapter(){
        HibernateJpaVendorAdapter  vendor = new HibernateJpaVendorAdapter();
        vendor.setDatabase(Database.MYSQL);
        vendor.setDatabasePlatform(this.dialect);
        vendor.setShowSql(Boolean.parseBoolean(this.showsql));
        vendor.setGenerateDdl(false);
        return vendor;
    }

    @Bean("transactionManagerMysql")
    public JpaTransactionManager builJpaTransactionMysql(){
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(builEntityMangerMysql());
        return jpaTransactionManager;
    }

    @Bean("persistenceExeptionTranlation")
    public PersistenceExceptionTranslationPostProcessor builPersistenceExceptionTranlationProcess(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
}

