package ar.ungs.infrastructure.data.entities;

import ar.ungs.domain.models.Schedule;
import ar.ungs.domain.models.inspection.Inspection;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InspectionEntity> inspections;

    @ManyToOne
    @JoinColumn
    private InspectorEntity inspector;

    private boolean notified;

    public ScheduleEntity(Schedule schedule) {
        if(schedule.getInspector() != null) setInspector(new InspectorEntity(schedule.getInspector()));
        inspections = new LinkedList<>();
        schedule.getInspections().forEach((k,v)->{
            inspections.add(new InspectionEntity(v));
        });
        setInspections(inspections);
        BeanUtils.copyProperties(schedule, this);
    }

    public Schedule toModel() {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(this, schedule);
        if(getInspector() != null) schedule.setInspector(inspector.toModel());
        Map<Integer, Inspection> inspectionMap = new HashMap<>();
        inspections.forEach(inspectionEntity -> {
            inspectionMap.put(inspectionEntity.getId(), inspectionEntity.toModel());
        });
        schedule.setInspections(inspectionMap);
        return schedule;
    }
}