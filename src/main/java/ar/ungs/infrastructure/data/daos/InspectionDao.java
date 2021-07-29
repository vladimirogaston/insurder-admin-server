package ar.ungs.infrastructure.data.daos;

import ar.ungs.infrastructure.data.entities.InspectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InspectionDao extends JpaRepository<InspectionEntity, String> {
}
