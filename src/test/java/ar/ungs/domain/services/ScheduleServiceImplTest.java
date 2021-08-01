package ar.ungs.domain.services;

import ar.ungs.domain.exceptions.DomainConstraintViolationException;
import ar.ungs.domain.exceptions.NotFoundException;
import ar.ungs.domain.models.Inspector;
import ar.ungs.domain.models.Schedule;
import ar.ungs.domain.models.inspection.Inspection;
import ar.ungs.domain.models.shared.AcceptanceLevel;
import ar.ungs.domain.models.shared.Cancellation;
import ar.ungs.domain.models.shared.Component;
import ar.ungs.domain.models.shared.Vehicle;
import ar.ungs.domain.out_ports.SchedulePersistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceImplTest {

    final int INSPECTOR_ID = 1;
    final int SCHEDULE_ID = 2;
    final int INSPECTION_ID = 1;
    final Component component = Component.builder()
            .brand("xxx")
            .code("xxx2")
            .acceptanceLevel(AcceptanceLevel.BAD)
            .build();
    Inspection prepared;
    Schedule schedule;

    @Mock
    SchedulePersistence schedulePersistence;

    @InjectMocks
    ScheduleServiceImpl scheduleService;

    @BeforeEach
    void setUp() {
        prepared = new Inspection();
        prepared.setId(1);
        prepared.prepare(Vehicle.builder().brand("ccc").location("ccc").build());
        schedule = new Schedule(Inspector.builder().id(INSPECTOR_ID).available(true).build());
        schedule.setId(SCHEDULE_ID);
        schedule.plan(prepared);
    }

    @Test
    void constructionTest() {
        Assertions.assertNotNull(schedulePersistence);
    }

    @Test
    void readNotNotifiedByInspector() {
        Mockito.when(schedulePersistence.readNotNotifiedByInspector(INSPECTOR_ID))
        .thenReturn(java.util.Optional.ofNullable(schedule));
        Assertions.assertNotNull(scheduleService.readNotNotifiedByInspector(INSPECTOR_ID));
        Mockito.when(schedulePersistence.readNotNotifiedByInspector(INSPECTOR_ID + 1))
                .thenReturn(java.util.Optional.empty());
        Assertions.assertThrows(NotFoundException.class, ()->{
            scheduleService.readNotNotifiedByInspector(INSPECTOR_ID + 1);
        });
    }

    @Test
    void registerComponent() {
        final int NOT_EXISTENT_SCHEDULE = SCHEDULE_ID + 1;
        Mockito.when(schedulePersistence.findById(NOT_EXISTENT_SCHEDULE))
                .thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, ()->{
            scheduleService.registerComponent(NOT_EXISTENT_SCHEDULE, INSPECTION_ID, component);
        });
        Mockito.when(schedulePersistence.findById(SCHEDULE_ID))
                .thenReturn(Optional.ofNullable(schedule));
        Assertions.assertThrows(NotFoundException.class, ()->{
            scheduleService.registerComponent(SCHEDULE_ID, INSPECTION_ID + 1, component);
        });
    }

    @Test
    void registerComponentFailsWhenInspectionIsClosed() {
        Mockito.when(schedulePersistence.findById(SCHEDULE_ID))
                .thenReturn(Optional.ofNullable(schedule));
        Mockito.when(schedulePersistence.save(schedule)).thenReturn(Optional.ofNullable(schedule));
        scheduleService.registerComponent(SCHEDULE_ID, INSPECTION_ID, component);
        scheduleService.close(SCHEDULE_ID, INSPECTION_ID);
        Assertions.assertThrows(UnsupportedOperationException.class, ()->{
            scheduleService.close(SCHEDULE_ID, INSPECTION_ID);
        });
        Assertions.assertThrows(UnsupportedOperationException.class, ()->{
            scheduleService.registerComponent(SCHEDULE_ID, INSPECTION_ID, component);
        });
        Assertions.assertThrows(UnsupportedOperationException.class, ()->{
            scheduleService.cancel(SCHEDULE_ID, INSPECTION_ID, Cancellation.builder().build());
        });
    }

    @Test
    void cancel() {
        Mockito.when(schedulePersistence.findById(SCHEDULE_ID))
                .thenReturn(Optional.ofNullable(schedule));
        Mockito.when(schedulePersistence.save(schedule)).thenReturn(Optional.ofNullable(schedule));
        Assertions.assertDoesNotThrow(()->{
            scheduleService.cancel(SCHEDULE_ID, INSPECTION_ID, Cancellation.builder().build());
        });
        Assertions.assertThrows(UnsupportedOperationException.class, ()->{
            scheduleService.cancel(SCHEDULE_ID, INSPECTION_ID, Cancellation.builder().build());
        });
        Assertions.assertThrows(UnsupportedOperationException.class, ()->{
            scheduleService.close(SCHEDULE_ID, INSPECTION_ID);
        });
    }

    @Test
    void notifySchedule() {
        Mockito.when(schedulePersistence.findById(SCHEDULE_ID))
                .thenReturn(Optional.ofNullable(schedule));
        Assertions.assertThrows(DomainConstraintViolationException.class, ()->{
            scheduleService.notifySchedule(SCHEDULE_ID);
        });
        scheduleService.cancel(SCHEDULE_ID, INSPECTION_ID, Cancellation.builder().build());
        Assertions.assertDoesNotThrow(()->{
            scheduleService.notifySchedule(SCHEDULE_ID);
        });
    }
}