package ar.ungs.infrastructure.data.daos;

import ar.ungs.DatabaseSeederTestContextConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@TestPropertySource("classpath:application-qa.properties")
@Import(DatabaseSeederTestContextConfiguration.class)
class InspectionDaoTest {
}