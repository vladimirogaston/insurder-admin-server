package ar.ungs.domain.models;

import ar.ungs.domain.models.inspection.Inspection;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

@NoArgsConstructor
@Component
public class DefaultSchedullerStrategy implements SchedullerStrategy {

    @Override
    public Set<Schedule> distributeWorkOverAvailableInspectors(Queue<Inspector> inspectors, Queue<Inspection> inspections) {
        if(inspectors.isEmpty() || inspections.isEmpty()) throw new UnsupportedOperationException();
        Set<Schedule> schedules = new HashSet<>();
        do {
            Inspector inspector = inspectors.poll();
            int count = 0;
            Schedule schedule = new Schedule();
            while(count < Schedule.MAX && !inspections.isEmpty()) {
                Inspection inspection = inspections.poll();
                schedule.plan(inspection);
            }
            if (schedule.hasInspections()) {
                schedules.add(schedule);
            }
        } while (inspectors.isEmpty() || inspections.isEmpty());
        return schedules;
    }
}
