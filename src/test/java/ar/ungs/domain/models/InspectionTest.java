package ar.ungs.domain.models;

import ar.ungs.domain.models.inspection.Inspection;
import ar.ungs.domain.models.shared.Cancellation;
import ar.ungs.domain.models.shared.Component;
import ar.ungs.domain.models.shared.State;
import ar.ungs.domain.models.shared.Vehicle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InspectionTest {

    public static final int SCHEDULE_CODE = 3123;
    Inspection inspection;
    Component component;

    @BeforeEach
    void setUp() {
        component = Component.builder().build();
        inspection = new Inspection();
    }

    @Test
    void construction() {
        Assertions.assertEquals(inspection.getCurrentState(), State.PREPARED);
    }

    @Test
    void doTransition() {
        inspection.doTransition(State.PLANNED);
        Assertions.assertEquals(inspection.getCurrentState(), State.PLANNED);
   }

    @Test
    void prepare() {
        Vehicle vehicle = Vehicle.builder().build();
        inspection.prepare(vehicle);
        Assertions.assertEquals(inspection.getCurrentState(), State.PLANNED);
        Assertions.assertThrows(UnsupportedOperationException.class, ()->{
            inspection.prepare(vehicle);
        });
    }

    @Test
    void plan() {
        Vehicle vehicle = Vehicle.builder().build();
        inspection.prepare(vehicle);
        inspection.plan(SCHEDULE_CODE);
        Assertions.assertThrows(UnsupportedOperationException.class, ()->{
            inspection.plan(SCHEDULE_CODE);
        });
        Assertions.assertEquals(inspection.getCurrentState(), State.REGISTER);
    }

    @Test
    void register() {
        Vehicle vehicle = Vehicle.builder().build();
        inspection.prepare(vehicle);
        inspection.plan(SCHEDULE_CODE);
        inspection.register(component);
        Assertions.assertEquals(inspection.getCurrentState(), State.REGISTER);
    }

    @Test
    void close() {
        Vehicle vehicle = Vehicle.builder().build();
        inspection.prepare(vehicle);
        inspection.plan(SCHEDULE_CODE);
        inspection.register(component);
        inspection.close();
        Assertions.assertThrows(UnsupportedOperationException.class, ()->{
            inspection.close();
        });
    }

    @Test
    void testClose() {
        Vehicle vehicle = Vehicle.builder().build();
        inspection.prepare(vehicle);
        inspection.plan(SCHEDULE_CODE);
        inspection.close(Cancellation.builder().build());
        Assertions.assertEquals(inspection.getCurrentState(), State.CLOSED);
    }
}