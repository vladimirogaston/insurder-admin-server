package ar.ungs.infrastructure.data.persistence;

import ar.ungs.domain.models.Schedule;
import ar.ungs.domain.out_ports.SchedulePersistence;
import ar.ungs.infrastructure.data.daos.ScheduleDao;
import ar.ungs.infrastructure.data.entities.ScheduleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SchedulePersistenceJpa implements SchedulePersistence {

    private ScheduleDao scheduleDao;

    @Autowired
    public SchedulePersistenceJpa(ScheduleDao scheduleDao) {
        this.scheduleDao = scheduleDao;
    }

    @Override
    public Optional<Schedule> readNotNotifiedByInspector(int inspectorCode) {
        return scheduleDao
                .findByNotifiedIsFalseAndInspectorId(inspectorCode)
                .map(ScheduleEntity::toModel);
    }

    @Override
    public Optional<Schedule> save(Schedule schedule) {
        Schedule target = scheduleDao.save(new ScheduleEntity(schedule)).toModel();
        return Optional.ofNullable(target);
    }

    @Override
    public Optional<Schedule> findById(int scheduleId) {
        return scheduleDao.findById(scheduleId).map(ScheduleEntity::toModel);
    }
}
