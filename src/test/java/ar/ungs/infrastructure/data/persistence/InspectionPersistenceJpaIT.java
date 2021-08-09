package ar.ungs.infrastructure.data.persistence;

import ar.ungs.DatabaseGraph;
import ar.ungs.DatabaseSeeder;
import ar.ungs.DatabaseSeederTestContextConfiguration;
import ar.ungs.domain.models.inspection.Inspection;
import ar.ungs.domain.out_ports.InspectionPersistence;
import ar.ungs.infrastructure.data.daos.InspectionDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@DataJpaTest
@ActiveProfiles("qa")
@Import({DatabaseSeederTestContextConfiguration.class})
class InspectionPersistenceJpaIT {

    @Autowired
    private InspectionPersistence inspectionPersistence;

    @Autowired
    private DatabaseSeeder databaseSeeder;

    @Test
    void testConstructor() {
        Assertions.assertNotNull(inspectionPersistence);
    }

    @Test
    void testSave() {
        DatabaseGraph graph = databaseSeeder.loadDatabaseGraph();
        Inspection inspection = graph.getInspections().get(0).toModel();
        Assertions.assertNotNull(inspection);
        Optional<Inspection> saved = inspectionPersistence.save(inspection);
        Assertions.assertTrue(saved.isPresent());
    }

    @TestConfiguration
    public static class InspectionPersistenceTestContextConfig {

        @Bean
        public InspectionPersistence inspectionPersistence(@Autowired InspectionDao inspectionDao) {
            return new InspectionPersistenceJpa(inspectionDao);
        }
    }
}