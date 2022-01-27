package com.orion.cafeorion.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dmitriy
 * @since 27.01.2022
 */
@Configuration
@OpenAPIDefinition(info = @Info(title = "Swagger service",
        version = "1.0.0",
        description = "Let me show you something"))
public class SwaggerConfiguration {
}
