package ar.ungs.infrastructure.data.daos;

import ar.ungs.infrastructure.data.entities.InspectorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InspectorDao extends CrudRepository<InspectorEntity, Integer> {

    List<InspectorEntity> findByAvailableIsTrue();
}
