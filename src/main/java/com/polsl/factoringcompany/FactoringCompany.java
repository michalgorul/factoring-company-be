package com.polsl.factoringcompany;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The type Factoring company. The main class that runs whole Spring Boot application
 */
@SpringBootApplication
@EnableSwagger2
@EnableScheduling
public class FactoringCompany {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(FactoringCompany.class, args);
    }

    /**
     * Creates RestTemplate bean.
     *
     * @return the rest template object
     */
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
