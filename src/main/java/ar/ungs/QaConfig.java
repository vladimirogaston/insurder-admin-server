package ar.ungs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("qa")
@PropertySource({"classpath:application-qa.properties"})
public class QaConfig {
}
