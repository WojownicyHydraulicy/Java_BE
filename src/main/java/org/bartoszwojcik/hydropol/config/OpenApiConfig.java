package org.bartoszwojcik.hydropol.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for customizing the OpenAPI documentation using SpringDoc.
 * <p>
 * Defines the base server URL that appears in the generated Swagger UI.
 * </p>
 *
 * <p>
 * This configuration is particularly useful when the backend is deployed under a specific domain
 * or behind a reverse proxy.
 * </p>
 *
 * @see <a href="https://springdoc.org/">SpringDoc OpenAPI Documentation</a>
 */
@Configuration
public class OpenApiConfig {

    /**
     * Creates a custom {@link OpenAPI} bean with the server URL configured.
     *
     * @return the customized {@link OpenAPI} instance
     */
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .addServersItem(new Server().url("https://users-management-api-409909044870.europe-central2.run.app"));
    }
}
