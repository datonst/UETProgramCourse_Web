package com.futuresubject.admin.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// chưa hiểu dòng code này làm gì
//https://www.youtube.com/watch?v=A_RWUcTqHBI&t=1s
@OpenAPIDefinition
@Configuration
public class SpringdocConfig {
    @Bean
    public OpenAPI baseOpenAPI() {
        return new OpenAPI().info(new Info().title("Spring"));
    }
}
