package ar.ungs.infraestructure.data.daos;

import ar.ungs.infraestructure.data.entities.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleDao extends JpaRepository<ScheduleEntity, String> {
}
