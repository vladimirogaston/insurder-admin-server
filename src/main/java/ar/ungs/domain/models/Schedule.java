package ar.ungs.domain.models;

import ar.ungs.domain.exceptions.DomainConstraintViolationException;
import ar.ungs.domain.exceptions.NotFoundException;
import ar.ungs.domain.models.inspection.Inspection;
import ar.ungs.domain.models.shared.Cancellation;
import ar.ungs.domain.models.shared.Component;
import ar.ungs.domain.models.shared.State;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Schedule {

    @JsonIgnore
    public static int MAX = 6;
    private Integer id;
    private Map<Integer, Inspection> inspections;
    private Inspector inspector;
    private boolean notified;

    @Autowired
    private static SchedullerStrategy schedullerStrategy;

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

    public void register(int id, Component component) {
        Inspection inspection = findById(id);
        inspection.register(component);
    }

    public void close(int id) {
        Inspection inspection = findById(id);
        inspection.close();
    }

    public void cancel(int id, Cancellation cancellation) {
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

    public Inspection findById(int id) {
        Inspection inspection = getInspections().get(id);
        if(inspection == null) throw new NotFoundException("");
        return inspection;
    }

    public static Set<Schedule> makeScheduleSet(Queue<Inspector> inspectors, Queue<Inspection> inspections) {
        return schedullerStrategy.distributeWorkOverAvailableInspectors(inspectors, inspections);
    }

    public boolean hasInspections() {
        return !this.inspections.isEmpty();
    }
}
