package com.yam.funteer.common.config;

import com.fasterxml.classmate.TypeResolver;
import com.yam.funteer.common.MyPageable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	private final TypeResolver typeResolver = new TypeResolver();
	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
			.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(Pageable.class),
					typeResolver.resolve(MyPageable.class)))
			.groupName("yam")
			.apiInfo(apiInfo())
			.select()
			.apis(RequestHandlerSelectors.basePackage("com.yam.funteer"))
			.paths(PathSelectors.ant("/**/**/**"))
			.build()
			.securityContexts(Arrays.asList(securityContext()))
			.securitySchemes(Arrays.asList(apiKey()));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("funteer API")
			.description("SSAFY BUK E204")
			.version("1.0").build();
	}

	private ApiKey apiKey() {
		return new ApiKey("JWT", "Authorization", "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).build();
	}

	private List<SecurityReference> defaultAuth(){
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] auth = new AuthorizationScope[1];
		auth[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("JWT", auth));
	}
}
