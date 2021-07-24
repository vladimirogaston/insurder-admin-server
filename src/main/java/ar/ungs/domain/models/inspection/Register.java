package ar.ungs.domain.models.inspection;

import ar.ungs.domain.exceptions.DomainConstraintViolationException;
import ar.ungs.domain.models.shared.State;
import ar.ungs.domain.models.shared.Vehicle;
import ar.ungs.domain.models.shared.Cancellation;
import ar.ungs.domain.models.shared.Component;

import java.util.Date;

public class Register extends AbstractInspectionState {

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
        inspection.getComponents().add(component);
    }

    @Override
    public void close(Inspection inspection, Cancellation cancellation) {
        inspection.setCancellation(cancellation);
        doClose(inspection);
    }

    @Override
    public void close(Inspection inspection) {
        if(inspection.getComponents().isEmpty()) throw new DomainConstraintViolationException();
        doClose(inspection);
    }

    private void doClose(Inspection inspection) {
        inspection.setClosingDate(new Date());
        inspection.doTransition(State.CLOSED);
    }
}
