package ar.ungs.domain.services;

import ar.ungs.domain.exceptions.NotFoundException;
import ar.ungs.domain.in_ports.ScheduleService;
import ar.ungs.domain.models.Schedule;
import ar.ungs.domain.models.shared.Cancellation;
import ar.ungs.domain.models.shared.Component;
import ar.ungs.domain.out_ports.SchedulePersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService{

    private SchedulePersistence schedulePersistence;

    @Autowired
    public ScheduleServiceImpl(SchedulePersistence schedulePersistence) {
        this.schedulePersistence = schedulePersistence;
    }

    @Override
    public Optional<Schedule> readNotNotifiedByInspector(int inspectorCode) {
        Schedule schedule = schedulePersistence.readNotNotifiedByInspector(inspectorCode).orElseThrow(()->new NotFoundException(""));
        return Optional.ofNullable(schedule);
    }

    @Override
    public void cancel(int scheduleId, int inspectionId, Cancellation cancellation) {
        Schedule schedule = schedulePersistence.findById(scheduleId).orElseThrow(()->new NotFoundException(""));
        schedule.cancel(inspectionId, cancellation);
        schedulePersistence.save(schedule);
    }

    @Override
    public void close(int scheduleId, int inspectionId) {
        Schedule schedule = schedulePersistence.findById(scheduleId).orElseThrow(()->new NotFoundException(""));
        schedule.close(inspectionId);
        schedulePersistence.save(schedule);
    }

    @Override
    public void registerComponent(int scheduleId, int inspectionId, Component component) {
        Schedule schedule = schedulePersistence.findById(scheduleId).orElseThrow(()->new NotFoundException(""));
        schedule.register(inspectionId, component);
        schedulePersistence.save(schedule);
    }

    @Override
    public void notifySchedule(int scheduleId) {
        Schedule schedule = schedulePersistence.findById(scheduleId).orElseThrow(()->new NotFoundException(""));
        schedule.notifySchedule();
        schedulePersistence.save(schedule);
    }
}