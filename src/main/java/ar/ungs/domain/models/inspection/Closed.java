package ar.ungs.domain.models.inspection;

import ar.ungs.domain.models.shared.Component;
import ar.ungs.domain.models.shared.Vehicle;
import ar.ungs.domain.models.shared.Cancellation;

public class Closed extends AbstractInspectionState {

    @Override
    public void prepare(Inspection inspection, Vehicle vehicle) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void plan(Inspection inspection, String scheduleCode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void register(Inspection inspection, Component component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void close(Inspection inspection, Cancellation cancellation) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void close(Inspection inspection) {
        throw new UnsupportedOperationException();
    }
}
