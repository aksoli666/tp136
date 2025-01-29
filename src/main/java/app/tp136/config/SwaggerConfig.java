package app.tp136.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Antiques Marketplace API Documentation")
                        .version("1.0.0")
                        .description("""
                                This API provides a comprehensive set of endpoints 
                                for managing the Antiques Marketplace platform.\s\s
                                It supports user authentication, product listings, 
                                order management, payments, discussions,\s\s
                                inspections, exhibitions, and more.\s\s
                                
                                Designed for seamless integration, 
                                the API allows clients to retrieve, 
                                update, and manage marketplace data efficiently.\s\s
                                Explore the endpoints to understand 
                                how to interact with the system effectively.
                                """)
                        .contact(new Contact()
                                .name("Oleksandra Dorofieieva")
                                .email("olexsandradorofeieiva@gmail.com")
                                .url("https://github.com/aksoli666"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                .addServersItem(new Server()
                        .description("Production ENV")
                        .url("https://tp136-production.up.railway.app/api"))
                .addServersItem(new Server()
                        .description("Local ENV")
                        .url("http://localhost:8080/api"));
    }
}
