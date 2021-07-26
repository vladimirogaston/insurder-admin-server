package ar.ungs.infraestructure.data.entities;

import ar.ungs.domain.models.Schedule;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class ScheduleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany
    private List<InspectionEntity> inspections;

    @ManyToOne
    private InspectorEntity inspector;

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