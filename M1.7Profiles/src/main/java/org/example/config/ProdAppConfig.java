package org.example.config;

import org.example.EnvPrinter;
import org.example.ProdEnvPrinter;
import org.springframework.context.annotation.*;

@Configuration
@PropertySources(
        value = {
                @PropertySource("classpath:application-prod.properties")
        })
@Profile("prod")
public class ProdAppConfig {

    @Bean
    public EnvPrinter envPrinter() {
        return new ProdEnvPrinter();
    }
}
