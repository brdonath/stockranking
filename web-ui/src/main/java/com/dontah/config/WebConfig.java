package com.dontah.config;

import com.planetj.servlet.filter.compression.CompressingFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.servlet.Filter;

/**
 * Created by Bruno on 24/07/14.
 */
@Configuration
@EnableAutoConfiguration(
        exclude = {
                HibernateJpaAutoConfiguration.class})
@ComponentScan("com.dontah")
@PropertySource({"application.properties", "db.properties"})
public class WebConfig {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WebConfig.class, args);
    }

    @Bean
    public Filter compressingFilter() {
        CompressingFilter compressingFilter = new CompressingFilter();
        return compressingFilter;
    }

}
