package ar.ungs.infrastructure.data.daos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@TestPropertySource("classpath:application-qa.properties")
class InspectorDaoTest {

    @Autowired
    InspectorDao inspectorDao;

    @Test
    void testConstructor() {
        Assertions.assertNotNull(inspectorDao);
    }
}