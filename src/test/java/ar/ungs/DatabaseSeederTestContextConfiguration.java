package ar.ungs;

import ar.ungs.infrastructure.data.daos.InspectorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class DatabaseSeederTestContextConfiguration {

    @Autowired
    private InspectorDao inspectorDao;

    @Bean
    public DatabaseSeeder databaseSeeder() {
        return new DatabaseSeeder(inspectorDao);
    }
}
