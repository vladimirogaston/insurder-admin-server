package ar.ungs.domain.models.inspection;

import ar.ungs.domain.models.shared.State;

public class StateCreator {
    public AbstractInspectionState make(State state) {
        AbstractInspectionState retAbstractInspectionState = null;
        if(state == State.PREPARED) {
            retAbstractInspectionState = new Prepared();
        } else if(state == State.PLANNED) {
            retAbstractInspectionState = new Planned();
        } else if(state == State.REGISTER) {
            retAbstractInspectionState = new Register();
        } else if(state == State.CLOSED) {
            retAbstractInspectionState = new Closed();
        }
        return retAbstractInspectionState;
    }
}
