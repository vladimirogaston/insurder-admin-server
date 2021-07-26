package ar.ungs.infraestructure.data.daos;

import ar.ungs.infraestructure.data.entities.InspectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InspectionDao extends JpaRepository<InspectionEntity, String> {
}
