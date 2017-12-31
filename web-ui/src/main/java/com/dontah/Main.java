package com.dontah;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.velocity.VelocityConfig;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityView;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import java.util.Properties;

/**
 * Created by Bruno on 24/07/14.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("com.dontah")
@EnableJpaRepositories("com.dontah.repository")
@EntityScan("com.dontah.domain")
@EnableCaching
@Scope(proxyMode = ScopedProxyMode.NO)
public class Main {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }

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
        velocityConfig.setResourceLoaderPath("classpath:/templates/");
        Properties velocityProperties = new Properties();
        velocityProperties.put("velocimacro.permissions.allow.inline.to.replace.global",true);
        velocityProperties.put("velocimacro.library.autoreload",true);
        velocityProperties.put("file.resource.loader.cache",true);
        velocityConfig.setVelocityProperties(velocityProperties);
        return velocityConfig;
    }

}
