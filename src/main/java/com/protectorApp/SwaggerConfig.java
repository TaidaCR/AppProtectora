package com.protectorApp;

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
public class SwaggerConfig {
	@Bean
	Docket createApiRestDoc() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.protectorApp"))
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("App para manejar datos animales protectoras")
				.description("Para obtener detalles mas alla de lo que exhibe esta API contactar con nosotros")
				.termsOfServiceUrl("https://github.com/TaidaCR/ProyectoFinal")
				.contact(new Contact("Taida Carmona", "www.taida.com", "taidacrm@gmail.com"))
				.build();
	}
}
