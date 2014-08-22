package com.dontah.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
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
@EnableWebMvc
@ComponentScan("com.dontah")
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        VelocityViewResolver resolver = new VelocityViewResolver();
        resolver.setPrefix("");
        resolver.setSuffix(".vm");
        resolver.setViewClass(VelocityView.class);
        resolver.setExposeSpringMacroHelpers(true);
        resolver.setToolboxConfigLocation("/WEB-INF/tools.xml");
        resolver.setContentType("text/html; charset=utf-8");
        return resolver;
    }

    @Bean
    public VelocityConfig velocityConfigurer(){
        VelocityConfigurer velocityConfig = new VelocityConfigurer();
        velocityConfig.setResourceLoaderPath("/WEB-INF/views/");
        velocityConfig.setVelocityProperties(velocityProperties());
        return velocityConfig;
    }

    Properties velocityProperties(){
        return new Properties(){
            {
                put("velocimacro.permissions.allow.inline.to.replace.global",true);
                put("velocimacro.library.autoreload",true);
                put("file.resource.loader.cache",true);
                put( "input.encoding", "UTF-8" );
                put( "output.encoding", "UTF-8" );
            }
        };
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);;
        registry.addResourceHandler("/bower_components/**").addResourceLocations("/bower_components/").setCachePeriod(31556926);;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}
