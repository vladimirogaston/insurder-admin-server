package ar.ungs.infraestructure.data.entities;

import ar.ungs.domain.models.Inspector;
import ar.ungs.domain.models.Schedule;
import ar.ungs.domain.models.inspection.Inspection;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Map;

@Data
@NoArgsConstructor
@Document
public class ScheduleEntity {
    @Id
    private String id;
    @DBRef
    private Map<String, Inspection> inspections;
    @DBRef
    private Inspector inspector;
    private boolean notified;

    public ScheduleEntity(Schedule schedule) {
        BeanUtils.copyProperties(schedule, this);
    }

    public Schedule toModel() {
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(this, schedule);
        return schedule;
    }
}