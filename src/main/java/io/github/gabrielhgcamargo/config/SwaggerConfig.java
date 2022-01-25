package io.github.gabrielhgcamargo.config;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("io.github.gabrielhgcamargo.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Grade Registration Management System - API")
                .description("Grade registration management system, where it is possible to register specific grades of specific students." +
                        "\n\nAuthentication : To use Aluno/Notas controllers and their HTTP methods, first you have to get registered in User Controller." +
                        "\n\n To 'Teacher' permissions, use True on the field 'admin'. To 'student', use false.\n\nTeacher role can use all HTTP methods, and " +
                        "Student role can just save a new student in AlunoController." + "\n\nBefore registering the test grade, it is necessary to register the student responsible for that grade." +
                        "\n\nStudent and User ID are AUTO-INCREMENT !!!")
                .version("1.0")
                .contact(contact())
                .build();
    }

    private Contact contact(){
        return new Contact("Gabriel Henrique Garcia Camargo",
                "https://github.com/gabrielhgcamargo",
                "gacamargos4@gmail.com");
    }
}
