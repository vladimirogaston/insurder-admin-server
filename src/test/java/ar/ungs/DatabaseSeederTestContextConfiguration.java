package ar.ungs;

import ar.ungs.infrastructure.data.daos.InspectionDao;
import ar.ungs.infrastructure.data.daos.InspectorDao;
import ar.ungs.infrastructure.data.daos.ScheduleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class DatabaseSeederTestContextConfiguration {

    @Autowired
    private InspectorDao inspectorDao;

    @Bean
    public DatabaseSeeder databaseSeeder(@Autowired InspectionDao inspectionDao,
                                         @Autowired ScheduleDao scheduleDao) {
        return new DatabaseSeeder(inspectorDao, inspectionDao,scheduleDao);
    }
}
