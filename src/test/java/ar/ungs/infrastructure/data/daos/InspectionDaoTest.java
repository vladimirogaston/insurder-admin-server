package ar.ungs.infrastructure.data.daos;

import ar.ungs.DatabaseSeeder;
import ar.ungs.DatabaseSeederTestContextConfiguration;
import ar.ungs.domain.models.shared.State;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@Import(DatabaseSeederTestContextConfiguration.class)
@ActiveProfiles("qa")
class InspectionDaoTest {

    @Autowired
    InspectionDao inspectionDao;

    @Autowired
    DatabaseSeeder seeder;

    @Test
    void testFindPreparedNotFails() {
        seeder.seedDatabase();
        Assertions.assertNotNull(inspectionDao);
        Assertions.assertFalse(inspectionDao.findByCurrentStateEquals(State.PREPARED).isEmpty());
        Assertions.assertTrue(inspectionDao.findByCurrentStateEquals(State.CLOSED).isEmpty());
    }
}