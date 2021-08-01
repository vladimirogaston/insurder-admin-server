package ar.ungs.infrastructure.data.daos;

import ar.ungs.infrastructure.data.entities.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleDao extends JpaRepository<ScheduleEntity, String> {

    Optional<ScheduleEntity> findByInspectorIdAndNotifiedIsFalse(int id);
}
