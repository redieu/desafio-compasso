package com.example.desafio.rest.demo.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.desafio.rest.demo.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .globalResponseMessage(RequestMethod.GET, mensagensRespostaGet());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Bem vindo a API Desafio Técnico Compasso")
                .description("Microsserviço REST para consumo e persistência de dados de produtos.")
                .version("0.0.1")
                .build();
    }

    private List<ResponseMessage> mensagensRespostaGet() {

        List<ResponseMessage> erros = new ArrayList<>();
        erros.add(new ResponseMessageBuilder()
            .code(404)
            .message("Produto não encontrado.")
            .build());
        erros.add(new ResponseMessageBuilder()
            .code(403)
            .message("Acesso não permitido.")
            .build());
        return erros;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
