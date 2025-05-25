package com.Locathon.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        String securitySchemeName = "JWT";

        // JWT 인증 방식 설정
        SecurityScheme securityScheme = new SecurityScheme()
                .name(securitySchemeName)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");

        // 보안 요구사항 설정 (모든 API에 기본 적용)
        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList(securitySchemeName);

        // OpenAPI 전체 구성
        return new OpenAPI()
                .components(new Components().addSecuritySchemes(securitySchemeName, securityScheme))
                .addSecurityItem(securityRequirement)
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("API Test")
                .description("Let's practice Swagger UI")
                .version("1.0.0");
    }
}
