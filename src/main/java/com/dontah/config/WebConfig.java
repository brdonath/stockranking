package com.dontah.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.velocity.VelocityConfig;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityView;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Bruno on 24/07/14.
 */
@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("com.dontah")
public class WebConfig {

    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        VelocityViewResolver resolver = new VelocityViewResolver();
        resolver.setPrefix("");
        resolver.setSuffix(".vm");
        resolver.setViewClass(VelocityView.class);
        resolver.setExposeSpringMacroHelpers(true);
        return resolver;
    }

    @Bean
    public VelocityConfig velocityConfigurer(){
        VelocityConfigurer velocityConfig = new VelocityConfigurer();
        velocityConfig.setResourceLoaderPath("/WEB-INF/views/");
        return velocityConfig;
    }

    @Bean
    public DataSource restDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:sqlite:mydb.sqlite");
        dataSource.setPassword("");
        dataSource.setUsername("");
        dataSource.setDriverClassName("org.sqlite.JDBC");
        return dataSource;
    }
    Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.hbm2ddl.auto", "validate");
                setProperty("hibernate.dialect","org.hibernate.dialect.SQLiteDialect");
//                setProperty("hibernate.show_sql", "true");
            }
        };
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(restDataSource());
        em.setPackagesToScan("com.dontah");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(hibernateProperties());

        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

}
