package ar.ungs.infraestructure.data.daos;

import ar.ungs.infraestructure.data.entities.InspectionEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InspectionDao extends MongoRepository<InspectionEntity, String> {
}
