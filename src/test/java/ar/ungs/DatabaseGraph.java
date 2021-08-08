package ar.ungs;

import ar.ungs.infrastructure.data.entities.InspectionEntity;
import ar.ungs.infrastructure.data.entities.InspectorEntity;
import ar.ungs.infrastructure.data.entities.ScheduleEntity;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class DatabaseGraph {

    private List<InspectorEntity> inspectors;
    private List<InspectionEntity> inspections;
    private List<ScheduleEntity> schedules;

    public DatabaseGraph() {
        setInspectors(new LinkedList<>());
        setInspections(new LinkedList<>());
        setSchedules(new LinkedList<>());
    }
}