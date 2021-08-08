package ar.ungs.infrastructure.data.daos;

import ar.ungs.infrastructure.data.entities.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScheduleDao extends JpaRepository<ScheduleEntity, Integer> {

    Optional<ScheduleEntity> findByNotifiedIsFalseAndInspectorIdEquals(int id);
}
