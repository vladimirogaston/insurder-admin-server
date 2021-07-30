package ar.ungs.infrastructure.data.entities;

import ar.ungs.domain.models.shared.AcceptanceLevel;
import ar.ungs.domain.models.shared.Component;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ComponentEntityTest {

    Component component;
    ComponentEntity entity;

    @BeforeEach
    void setUp() {
        component = Component.builder()
                .code("3123")
                .brand("xx1")
                .acceptanceLevel(AcceptanceLevel.BAD)
                .build();
        entity = new ComponentEntity(component);
    }

    @Test
    void toEntityConstruction() {
        Assertions.assertEquals(entity.getBrand(), component.getBrand());
        Assertions.assertEquals(entity.getCode(), component.getCode());
        Assertions.assertEquals(entity.getAcceptanceLevel(), component.getAcceptanceLevel());
    }

    @Test
    void toModel() {
        Component component = entity.toModel();
        Assertions.assertEquals(entity.getBrand(), component.getBrand());
        Assertions.assertEquals(entity.getCode(), component.getCode());
        Assertions.assertEquals(entity.getAcceptanceLevel(), component.getAcceptanceLevel());
    }
}