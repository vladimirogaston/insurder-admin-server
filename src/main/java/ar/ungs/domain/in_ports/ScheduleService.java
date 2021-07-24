package ar.ungs.domain.in_ports;

import ar.ungs.domain.models.Schedule;
import ar.ungs.domain.models.shared.Cancellation;
import ar.ungs.domain.models.shared.Component;

public interface ScheduleService {

    Schedule readNotNotifiedByInspector(String inspectorCode);

    void cancel(String scheduleId, String inspectionId, Cancellation cancellation);

    void close(String scheduleId, String inspectionId);

    void notifySchedule(String scheduleId);

    void registerComponent(String scheduleId, String inspectionId, Component component);
}
