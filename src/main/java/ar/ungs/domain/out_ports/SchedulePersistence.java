package ar.ungs.domain.out_ports;

import ar.ungs.domain.models.Schedule;

import java.util.Optional;

public interface SchedulePersistence {

    Optional<Schedule> readNotNotifiedByInspector(int inspectorCode);

    Optional<Schedule> save(Schedule schedule);

    Optional<Schedule> findById(int scheduleId);
}
