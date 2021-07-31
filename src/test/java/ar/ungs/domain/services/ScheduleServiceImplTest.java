package ar.ungs.domain.services;

import ar.ungs.domain.out_ports.SchedulePersistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class ScheduleServiceImplTest {

    @Mock
    SchedulePersistence schedulePersistence;

    @InjectMocks
    ScheduleServiceImpl scheduleService;

    @Test
    void constructionTest() {
        Assertions.assertNotNull(schedulePersistence);
    }

    @Test
    void readNotNotifiedByInspector() {
    }

    @Test
    void cancel() {
    }

    @Test
    void close() {
    }

    @Test
    void registerComponent() {
    }

    @Test
    void notifySchedule() {
    }
}