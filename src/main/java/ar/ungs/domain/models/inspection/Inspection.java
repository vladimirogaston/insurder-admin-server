package ar.ungs.domain.models.inspection;

import ar.ungs.domain.models.shared.Cancellation;
import ar.ungs.domain.models.shared.Component;
import ar.ungs.domain.models.shared.State;
import ar.ungs.domain.models.shared.Vehicle;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Inspection {

    private String id;
    private Vehicle vehicle;
    private List<Component> components;
    private Cancellation cancellation;
    private Date preparationDate;
    private Date planingDate;
    private String scheduleCode;
    private Date closingDate;
    private State currentState;

    public Inspection() {
        setComponents(new LinkedList<>());
        setCurrentState(State.PREPARED);
    }

    private AbstractInspectionState getState() {
        AbstractInspectionState retAbstractInspectionState = new StateCreator().make(getCurrentState());
        return retAbstractInspectionState;
    }

    public void doTransition(State state) {
        setCurrentState(state);
    }

    public void prepare(Vehicle vehicle) {
        getState().prepare(this,vehicle);
    }

    public void plan(String scheduleCode) {
        getState().plan(this,scheduleCode);
    }

    public void register(Component component) {
        getState().register(this,component);
    }

    public void close(Cancellation cancellation) {
        getState().close(this,cancellation);
    }

    public void close() {
        getState().close(this);
    }
}
