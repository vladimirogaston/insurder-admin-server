package ar.ungs.infrastructure.data.daos;

import ar.ungs.domain.models.shared.State;
import ar.ungs.infrastructure.data.entities.InspectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InspectionDao extends JpaRepository<InspectionEntity, Integer> {

    List<InspectionEntity> findByCurrentStateEquals(State state);
}
