package com.futuresubject.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan({"com.futuresubject.common.entity"})
public class WebBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebBackendApplication.class, args);
    }
}
