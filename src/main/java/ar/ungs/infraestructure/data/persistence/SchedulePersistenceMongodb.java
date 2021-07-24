package ar.ungs.infraestructure.data.persistence;

import ar.ungs.domain.exceptions.DomainConstraintViolationException;
import ar.ungs.domain.models.Schedule;
import ar.ungs.domain.out_ports.SchedulePersistence;
import ar.ungs.infraestructure.data.daos.ScheduleDao;
import ar.ungs.infraestructure.data.entities.ScheduleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class SchedulePersistenceMongodb implements SchedulePersistence {

    private ScheduleDao scheduleDao;

    @Autowired
    public SchedulePersistenceMongodb(ScheduleDao scheduleDao) {
        this.scheduleDao = scheduleDao;
    }

    /**
     * @implNote change this, let scheduleDao do the work for you
     * @param inspectorCode
     * @return
     */
    @Override
    public Optional<Schedule> readNotNotifiedByInspector(String inspectorCode) {
        List<Schedule> scheduleList = scheduleDao.findAll()
                .stream()
                .filter(scheduleEntity -> scheduleEntity.getInspector().getId().equals(inspectorCode))
        .map(ScheduleEntity::toModel).collect(Collectors.toList());
        if (scheduleList.size() > 1) throw new DomainConstraintViolationException();
        Schedule schedule = scheduleList.get(0);
        return Optional.ofNullable(schedule);
    }

    @Override
    public Optional<Schedule> save(Schedule schedule) {
        Schedule target = scheduleDao.save(new ScheduleEntity(schedule)).toModel();
        return Optional.ofNullable(target);
    }
}
