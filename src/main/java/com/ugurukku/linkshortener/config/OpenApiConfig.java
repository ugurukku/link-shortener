package com.ugurukku.linkshortener.config;


import com.ugurukku.linkshortener.model.property.OpenApiProperty;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OpenApiConfig {

    OpenApiProperty openApiProperty;

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title(openApiProperty.getTitle())
                        .description(openApiProperty.getDescription())
                        .version(openApiProperty.getVersion()));
    }


}
