package com.blueocn.ECommerceApplication.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
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
public class OpenApiConfig {
}
