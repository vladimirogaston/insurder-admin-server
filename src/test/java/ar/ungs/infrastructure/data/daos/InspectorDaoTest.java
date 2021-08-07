package ar.ungs.infrastructure.data.daos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Profile;

@Profile("qa")
@DataJpaTest
class InspectorDaoTest {

    @Autowired
    private InspectorDao inspectorDao;

    @Test
    void testConstructor() {
        Assertions.assertNotNull(inspectorDao);
    }
}