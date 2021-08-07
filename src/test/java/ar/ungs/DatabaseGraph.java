package ar.ungs;

import ar.ungs.infrastructure.data.entities.ComponentEntity;
import ar.ungs.infrastructure.data.entities.InspectionEntity;
import ar.ungs.infrastructure.data.entities.InspectorEntity;
import ar.ungs.infrastructure.data.entities.ScheduleEntity;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class DatabaseGraph {

    private List<InspectorEntity> inspectors;
    private List<ScheduleEntity> schedules;
    private List<InspectionEntity> inspections;

    public DatabaseGraph() {
        setInspectors(new LinkedList<>());
        setSchedules(new LinkedList<>());
        setInspections(new LinkedList<>());
    }
}
