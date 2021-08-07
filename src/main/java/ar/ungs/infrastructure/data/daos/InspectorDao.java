package ar.ungs.infrastructure.data.daos;

import ar.ungs.infrastructure.data.entities.InspectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InspectorDao extends JpaRepository<InspectorEntity, Integer> {
}
