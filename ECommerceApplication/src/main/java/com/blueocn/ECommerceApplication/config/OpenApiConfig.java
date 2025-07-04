package com.blueocn.ECommerceApplication.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "BlueOCN",
                        email = "BlueOCN@email.com",
                        url = "https://github.com/BlueOCN"
                ),
                description = "Simple E-Commerce app using microservices: Product (catalog), Order (manage orders), Payment (mock processing), and User (auth & profiles). Each service runs independently, communicates with others, and registers with Eureka for discovery.",
                title = "Microservices Based E Commerce Application",
                version = "1.0",
                license = @License(
                        name = "MIT",
                        url = "https://github.com/BlueOCN/JFS-Project-Building-a-Microservices-Based-E-Commerce-Application?tab=MIT-1-ov-file"
                ),
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8080"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT authentication using Bearer token",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
@SecurityScheme(
        name = "basicAuth",
        description = "Basic authentication with username and password",
        scheme = "basic",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
