package com.nexily.inventory.inventoryenigma.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI inventoryAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Inventory Enigma API")
                        .description("Smart Inventory & Billing System")
                        .version("1.0.0"));
    }
}
