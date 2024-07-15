package com.urlshortener.config.web;

import com.urlshortener.auth.model.AuthUser;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.urlshortener.util.HeaderUtil.READYS_AUTH_HEADER_KEY;

/**
 * you can see (<a href="http://localhost:8080/swagger-ui/index.html">swagger</a>)
 */
@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {
    @PostConstruct
    public void init() {
        SpringDocUtils.getConfig().addRequestWrapperToIgnore(AuthUser.class);
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        READYS_AUTH_HEADER_KEY,
                                        new SecurityScheme()
                                                .type(SecurityScheme.Type.APIKEY)
                                                .in(SecurityScheme.In.HEADER)
                                                .name(READYS_AUTH_HEADER_KEY)
                                                .description("인증이 필요한 경우 ex) X-READYS-AUTH-TOKEN xxxxxxx")
                                )
                )
                .security(List.of(new SecurityRequirement().addList(READYS_AUTH_HEADER_KEY)))
                .info(swaggerInfo());
    }

    private Info swaggerInfo() {
        return new Info().title("Readys API").description("url shortener");
    }
}
