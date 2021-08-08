package ar.ungs.infrastructure.data.daos;

import ar.ungs.DatabaseSeeder;
import ar.ungs.DatabaseSeederTestContextConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@TestPropertySource("classpath:application-qa.properties")
@Import(DatabaseSeederTestContextConfiguration.class)
class ScheduleDaoTest {

    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private DatabaseSeeder databaseSeeder;

    @Test
    void findByNotifiedIsFalseAndInspectorIdEquals() {
        databaseSeeder.seedDatabase();
        Assertions.assertNotNull(scheduleDao);
        Assertions.assertFalse(scheduleDao.findAll().isEmpty());
        scheduleDao.findAll().forEach(schedule->{
            System.out.println(schedule.getInspector().getId());
        });
        //Assertions.assertFalse(scheduleDao.findByNotifiedIsFalseAndInspectorIdEquals(1).isPresent());
    }
}