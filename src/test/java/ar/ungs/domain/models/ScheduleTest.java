package ar.ungs.domain.models;

import ar.ungs.domain.exceptions.DomainConstraintViolationException;
import ar.ungs.domain.exceptions.NotFoundException;
import ar.ungs.domain.models.inspection.Inspection;
import ar.ungs.domain.models.shared.Cancellation;
import ar.ungs.domain.models.shared.Component;
import ar.ungs.domain.models.shared.AcceptanceLevel;
import ar.ungs.domain.models.shared.Vehicle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ScheduleTest {

    public static final int ID = 123;
    List<Inspection> inspections;
    List<Inspector> inspectors;
    final int AVAILABLE = 0;
    final int NOT_AVAILABLE = 1;
    Component component;

    @BeforeEach
    void setUp(){
        component = Component.builder()
                .code("23L")
                .acceptanceLevel(AcceptanceLevel.BAD)
                .brand("xl3")
                .build();
        inspectors = new ArrayList<>();
        inspectors.add(Inspector.builder().available(true).id(ID).build());
        inspectors.add(Inspector.builder().available(false).id(ID).build());
        inspections = new ArrayList<>();
        for(int count = 0; count < 10; count++) {
            Inspection inspection = new Inspection();
            inspection.setId(count);
            inspection.prepare(Vehicle.builder().build());
            inspections.add(inspection);
        }
    }

    Schedule makeSchedule() {
        Inspector inspector = inspectors.get(AVAILABLE);
        Schedule schedule = new Schedule(inspector);
        for(int count = 0; count < Schedule.MAX; count++) {
            schedule.plan(inspections.get(count));
        }
        return schedule;
    }

    @Test
    void creation() {
        Inspector notAvailableInspector = inspectors.get(NOT_AVAILABLE);
        Assertions.assertThrows(DomainConstraintViolationException.class, ()->{
            Schedule schedule = new Schedule(notAvailableInspector);
        });
        Inspector inspector = inspectors.get(AVAILABLE);
        Schedule schedule = new Schedule(inspector);
        schedule.setId(ID);
        Assertions.assertNotNull(schedule.getInspector());
        Assertions.assertNotNull(schedule.getInspections());
    }

    @Test
    void plan() {
        Schedule schedule = makeSchedule();
        Assertions.assertFalse(schedule.getInspections().isEmpty());
        Assertions.assertEquals(schedule.getInspections().values().size(), Schedule.MAX);
        Assertions.assertThrows(DomainConstraintViolationException.class, ()->{
            final int index = inspections.size() - 1;
            schedule.plan(inspections.get(index));
        });
    }

    @Test
    void register() {
        Schedule schedule = makeSchedule();
        Assertions.assertThrows(NotFoundException.class, ()->{
            schedule.register(12, component);
        });
        schedule.register(1, component);
        Assertions.assertEquals(schedule.findById(1).getComponents().size(), 1);
    }

    @Test
    void cancel() {
        Schedule schedule = makeSchedule();
        for(int id = 0; id < Schedule.MAX; id++) {
            schedule.cancel(id, Cancellation.builder().build());
        }
        Assertions.assertTrue(schedule.canNotify());
        schedule.notifySchedule();
        Assertions.assertTrue(schedule.getInspector().isAvailable());
    }

    @Test
    void canNotify() {
        Schedule schedule = makeSchedule();
        Assertions.assertFalse(schedule.canNotify());
    }
}