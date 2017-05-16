package com.seal_de.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by sealde on 5/8/17.
 */
@EnableSwagger2
@Configuration
public class Swagger2Config {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.seal_de.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("试题录入审核系统接口文档")
                .description("question:试题录入审核后台系统。https://github.com/Deeeeeeeee/question-backstage")
                .termsOfServiceUrl("https://github.com/Deeeeeeeee/question-backstage")
                .version("0.0.1")
                .contact(new Contact("seal_de", "https://github.com/Deeeeeeeee", "seal.de@foxmail.com"))
                .build();
    }
}
