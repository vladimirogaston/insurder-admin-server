package ar.ungs.infrastructure.data.daos;

import ar.ungs.infrastructure.data.entities.InspectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InspectionDao extends JpaRepository<InspectionEntity, Integer> {
}
