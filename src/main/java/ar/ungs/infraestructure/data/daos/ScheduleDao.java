package ar.ungs.infraestructure.data.daos;

import ar.ungs.infraestructure.data.entities.ScheduleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScheduleDao extends MongoRepository<ScheduleEntity, String> {
}
