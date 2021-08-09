package ar.ungs.infrastructure.data.entities;

import ar.ungs.domain.models.inspection.Inspection;
import ar.ungs.domain.models.shared.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.LinkedList;

@ActiveProfiles("qa")
class InspectionEntityTest {

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
    InspectionEntity entity;

    @BeforeEach
    void setUp() {
        model = new Inspection();
        components.add(component);
        model.setComponents(components);
        model.setVehicle(vehicle);
        model.setCancellation(Cancellation.builder().description("x").registration(new Date()).build());
        entity = new InspectionEntity(model);
    }

    @Test
    void toModel() {
        Assertions.assertEquals(model.getCurrentState(), entity.getCurrentState());
        Assertions.assertEquals(model.getVehicle(), entity.getVehicle().toModel());
        Assertions.assertEquals(model.getVehicle().getBrand(), entity.getVehicle().toModel().getBrand());
        Assertions.assertEquals(model.getVehicle().getOwnerDni(), entity.getVehicle().toModel().getOwnerDni());
        Assertions.assertEquals(model.getVehicle().getLocation(), entity.getVehicle().toModel().getLocation());
        Assertions.assertEquals(model.getCancellation(), entity.getCancellation().toModel());
        Assertions.assertEquals(model.getCancellation().getDescription(), entity.getCancellation().toModel().getDescription());
        Assertions.assertEquals(model.getComponents().size(), entity.getComponents().size());
        Assertions.assertEquals(model.getComponents(), entity.toModel().getComponents());
        Assertions.assertEquals(model, entity.toModel());
    }
}