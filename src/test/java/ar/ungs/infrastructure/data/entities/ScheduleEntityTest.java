package ar.ungs.infrastructure.data.entities;

import ar.ungs.domain.models.Inspector;
import ar.ungs.domain.models.Schedule;
import ar.ungs.domain.models.inspection.Inspection;
import ar.ungs.domain.models.shared.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class ScheduleEntityTest {

    final Component component = Component.builder()
            .code("3")
            .acceptanceLevel(AcceptanceLevel.BAD)
            .brand("x")
            .build();
    LinkedList<Component> components = new LinkedList<>();
    final Vehicle vehicle = Vehicle.builder()
            .brand("x12")
            .location("11")
            .ownerDni(123L)
            .ownerPhone("1111")
            .patent("1d")
            .build();
    Inspection model;
    Map<Integer, Inspection> inspectionMap;
    Schedule schedule;

    @BeforeEach
    void setUp() {
        model = new Inspection();
        components.add(component);
        model.setComponents(components);
        model.setVehicle(vehicle);
        model.setCancellation(Cancellation.builder().description("x").registration(new Date()).build());
        model.setCurrentState(State.CLOSED);
        inspectionMap = new HashMap<>();
        inspectionMap.put(model.getId(), model);
        schedule = new Schedule();
        schedule.setInspector(Inspector.builder().id(123).available(false).build());
        schedule.setInspections(inspectionMap);
        schedule.setId(2);
        schedule.setNotified(false);
    }

    @Test
    void toModel() {
        ScheduleEntity scheduleEntity = new ScheduleEntity(schedule);
        Assertions.assertEquals(scheduleEntity.toModel().getInspector(), schedule.getInspector());
        Assertions.assertEquals(scheduleEntity.toModel().getId(), schedule.getId());
        Assertions.assertEquals(scheduleEntity.toModel().getInspections(), schedule.getInspections());
        Assertions.assertEquals(scheduleEntity.toModel(), schedule);
    }
}