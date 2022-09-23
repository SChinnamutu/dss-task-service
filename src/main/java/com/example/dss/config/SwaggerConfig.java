package com.example.dss.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("DSS Task Micro Service")
						.apiInfo(apiInfo()).select()
						.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
						.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.cloud")))
						.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.data.rest.webmvc")))
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
						.title("DSS Task Micro Service")
						.description("DSS Task  REST API's micro service developed in spring boot. "
								+ "This is the centralized micro service. Objective of this application is to "
								+ "expose end points for all task related operations.")
						.contact(new Contact("Sasikumar C", "", "sasic@dss.com"))
						.license("Apache License Version 2.0")
						.version("1.1.0")
				.build();
	}
	
	
	
}
