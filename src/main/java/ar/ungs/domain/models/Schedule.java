package ar.ungs.domain.models;

import ar.ungs.domain.exceptions.DomainConstraintViolationException;
import ar.ungs.domain.exceptions.NotFoundException;
import ar.ungs.domain.models.inspection.Inspection;
import ar.ungs.domain.models.shared.Cancellation;
import ar.ungs.domain.models.shared.Component;
import ar.ungs.domain.models.shared.State;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Data
@NoArgsConstructor
public class Schedule {

    @JsonIgnore
    public static int MAX = 6;
    private String id;
    private Map<String, Inspection> inspections;
    private Inspector inspector;
    private boolean notified;

    public Schedule(Inspector inspector) {
        if(!inspector.isAvailable()) throw new DomainConstraintViolationException();
        setInspections(new HashMap<>());
        setInspector(inspector);
        getInspector().setAvailable(false);
        setNotified(false);
    }

    public void plan(Inspection inspection) {
        if(getInspections().size() >= MAX) throw new DomainConstraintViolationException();
        inspection.plan(getId());
        getInspections().put(inspection.getId(), inspection);
    }

    public void register(String id, Component component) {
        Inspection inspection = findById(id);
        inspection.register(component);
    }

    public void close(String id) {
        Inspection inspection = findById(id);
        inspection.close();
    }

    public void cancel(String id, Cancellation cancellation) {
        Inspection inspection = findById(id);
        inspection.close(cancellation);
    }

    public boolean canNotify() {
        boolean ret = true;
        Iterator iterator = getInspections().values().iterator();
        while(iterator.hasNext()) {
            Inspection inspection = (Inspection) iterator.next();
            ret = ret && inspection.getCurrentState() == State.CLOSED;
        }
        return ret;
    }

    public void notifySchedule() {
        if(!canNotify()) throw new DomainConstraintViolationException();
        getInspector().setAvailable(true);
        setNotified(true);
    }

    public Inspection findById(String id) {
        Inspection inspection = getInspections().get(id);
        if(inspection == null) throw new NotFoundException("");
        return inspection;
    }
}
