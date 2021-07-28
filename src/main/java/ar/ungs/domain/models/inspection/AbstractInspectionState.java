package ar.ungs.domain.models.inspection;

import ar.ungs.domain.models.shared.Vehicle;
import ar.ungs.domain.models.shared.Cancellation;
import ar.ungs.domain.models.shared.Component;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class AbstractInspectionState {

    public abstract void prepare(Inspection inspection, Vehicle vehicle);

    public abstract void plan(Inspection inspection, int scheduleCode);

    public abstract void register(Inspection inspection, Component component);

    public abstract void close(Inspection inspection, Cancellation cancellation);

    public abstract void close(Inspection inspection);
}
