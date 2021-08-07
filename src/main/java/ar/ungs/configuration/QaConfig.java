package ar.ungs.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Profile("qa")
@Configuration
@PropertySource({"classpath:application-qa.properties"})
public class QaConfig {
}
