package ar.ungs.domain.services;

import ar.ungs.domain.exceptions.NotFoundException;
import ar.ungs.domain.in_ports.ScheduleService;
import ar.ungs.domain.models.Schedule;
import ar.ungs.domain.models.shared.Cancellation;
import ar.ungs.domain.models.shared.Component;
import ar.ungs.domain.out_ports.SchedulePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class ScheduleServiceImpl implements ScheduleService{

    private SchedulePersistence schedulePersistence;

    @Autowired
    public ScheduleServiceImpl(SchedulePersistence schedulePersistence) {
        this.schedulePersistence = schedulePersistence;
    }

    @Override
    public Schedule readNotNotifiedByInspector(String inspectorCode) {
        return schedulePersistence.readNotNotifiedByInspector(inspectorCode).orElseThrow(()->new NotFoundException(""));
    }

    @Override
    public void cancel(String scheduleId, String inspectionId, Cancellation cancellation) {
        Schedule schedule = readNotNotifiedByInspector(scheduleId);
        schedule.cancel(inspectionId, cancellation);
        schedulePersistence.update(schedule);
    }

    @Override
    public void close(String scheduleId, String inspectionId) {
        Schedule schedule = readNotNotifiedByInspector(scheduleId);
        schedule.close(inspectionId);
        schedulePersistence.update(schedule);
    }

    @Override
    public void registerComponent(String scheduleId, String inspectionId, Component component) {
        Schedule schedule = readNotNotifiedByInspector(scheduleId);
        schedule.close(inspectionId);
        schedulePersistence.update(schedule);
    }

    @Override
    public void notifySchedule(String scheduleId) {
        Schedule schedule = readNotNotifiedByInspector(scheduleId);
        schedule.notifySchedule();
        schedulePersistence.update(schedule);
    }

    private void doProcess(Consumer<Schedule> operation, String ...args) {
        Schedule schedule = readNotNotifiedByInspector(args[0]);
        operation.accept(schedule);
        schedulePersistence.update(schedule);
    }
}
