package com.br.fabio.api.kafka.api_kafka.aplication.config;

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

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.br.fabio.api.kafka.api_kafka.aplication.controller"))
                .paths(PathSelectors.regex("/orders.*"))		
                .build()
				.apiInfo( metaData() );

	}
	
	private ApiInfo metaData() { 
		
		return new ApiInfoBuilder()
				.title("Fast-Track API Documentation")
				.description("Crud")
				.version("2.0")
				.license(null)				
				.licenseUrl(null)				
				.contact(new Contact("Fabio Alves", "", "fabio.calves@telefonica.com"))
				.build();
  	 
    }
}
