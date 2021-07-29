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

    /**
     * TERMINAR ESTE CODIGO PORQUE MAGA NO ME DEJA TERMINARLO!
     * @param inspectors
     * @param inspections
     * @return
     */
    @Override
    public Set<Schedule> makeScheduleSet(Queue<Inspector> inspectors, Queue<Inspection> inspections) {
        if(inspectors.isEmpty() || inspections.isEmpty()) throw new UnsupportedOperationException();
        Set<Schedule> schedules = new HashSet<>();
        do {
            Inspector inspector = inspectors.poll();
            int count = 0;
            while(count < Schedule.MAX) {

            }
        }while (inspectors.isEmpty() || inspections.isEmpty());
        return null;
    }
}
