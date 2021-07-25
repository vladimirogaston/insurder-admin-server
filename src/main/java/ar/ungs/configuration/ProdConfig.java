package ar.ungs.configuration;

import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Profile("prod")
@PropertySource(value = "application-prod")
public class ProdConfig {
}
