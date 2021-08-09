package ar.ungs.infrastructure.data.persistence;

import ar.ungs.DatabaseGraph;
import ar.ungs.DatabaseSeeder;
import ar.ungs.DatabaseSeederTestContextConfiguration;
import ar.ungs.domain.models.Schedule;
import ar.ungs.domain.out_ports.SchedulePersistence;
import ar.ungs.infrastructure.data.daos.ScheduleDao;
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
class SchedulePersistenceJpaTest {

    @Autowired
    private SchedulePersistence schedulePersistence;

    @Autowired
    private DatabaseSeeder databaseSeeder;

    @Test
    void readNotNotifiedByInspector() {
        databaseSeeder.seedDatabase();
        Assertions.assertTrue(schedulePersistence.readNotNotifiedByInspector(1).isPresent());
        Assertions.assertFalse(schedulePersistence.readNotNotifiedByInspector(11).isPresent());
    }

    @Test
    void save() {
        DatabaseGraph graph = databaseSeeder.loadDatabaseGraph();
        Schedule schedule = graph.getSchedules().get(0).toModel();
        Optional<Schedule> saved = schedulePersistence.save(schedule);
        Assertions.assertTrue(saved.isPresent());
        Assertions.assertNotNull(saved.get().getId());
        Assertions.assertTrue(saved.get().hasInspections());
    }

    @TestConfiguration
    public static class SchedulePersistenceTestContextConfig {

        @Bean
        public SchedulePersistence schedulePersistence(@Autowired ScheduleDao scheduleDao) {
            return new SchedulePersistenceJpa(scheduleDao);
        }
    }
}