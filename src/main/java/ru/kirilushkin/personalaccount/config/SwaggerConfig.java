package ru.kirilushkin.personalaccount.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
  @Bean
  public Docket docket() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("ru.kirilushkin.personalaccount"))
        .paths(PathSelectors.any())
        .build()
        .securitySchemes(Arrays.asList(oauthSecuritySchema()))
        .securityContexts(Arrays.asList(securityContext()))
        ;
  }

  private OAuth oauthSecuritySchema() {
    List<AuthorizationScope> scopes = Arrays.asList(
        new AuthorizationScope("read", "read scope"),
        new AuthorizationScope("write", "write scope")
    );

    List<GrantType> grantTypes = Arrays.asList(
        new ResourceOwnerPasswordCredentialsGrant("/oauth/token")
    );

    return new OAuth("oauth2", scopes, grantTypes);
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder()
        .securityReferences(
                Arrays.asList(securityReference())
        )
        .build();
  }

  private SecurityReference securityReference() {
    return new SecurityReference(
        "oauth2",
        new AuthorizationScope[]{}
    );
  }
}
