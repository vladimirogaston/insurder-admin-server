package ar.ungs.configuration;

import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Profile("dev")
@PropertySource(value = "application-dev")
public class DevConfig {
}
