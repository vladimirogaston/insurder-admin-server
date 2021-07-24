package ar.ungs.domain.out_ports;

import ar.ungs.domain.models.Schedule;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchedulePersistence {

    Optional<Schedule> readNotNotifiedByInspector(String inspectorCode);

    Optional<Schedule> save(Schedule schedule);

    void update(Schedule schedule);
}
