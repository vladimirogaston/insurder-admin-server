package ar.ungs.domain.in_ports;

import ar.ungs.domain.models.Schedule;
import ar.ungs.domain.models.shared.Cancellation;
import ar.ungs.domain.models.shared.Component;

public interface ScheduleService {

    Schedule readNotNotifiedByInspector(int inspectorCode);

    void cancel(int scheduleId, int inspectionId, Cancellation cancellation);

    void close(int scheduleId, int inspectionId);

    void notifySchedule(int scheduleId);

    void registerComponent(int scheduleId, int inspectionId, Component component);
}
