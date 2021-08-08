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
class InspectorDaoTest {

    @Autowired
    InspectorDao inspectorDao;

    @Autowired
    DatabaseSeeder seeder;

    @Test
    void testConstructor() {
        Assertions.assertNotNull(inspectorDao);
        Assertions.assertNotNull(seeder);
    }

    @Test
    void testFindAll() {
        seeder.seedDatabase();
        Assertions.assertFalse(inspectorDao.findAll().isEmpty());
        final int AVAILABLE_INSPECTORS_COUNT = 3;
        Assertions.assertEquals(inspectorDao.findByAvailableIsTrue().size(), AVAILABLE_INSPECTORS_COUNT);
    }
}