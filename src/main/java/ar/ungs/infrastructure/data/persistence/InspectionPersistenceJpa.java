package ar.ungs.infrastructure.data.persistence;

import ar.ungs.domain.models.inspection.Inspection;
import ar.ungs.domain.out_ports.InspectionPersistence;
import ar.ungs.infrastructure.data.daos.InspectionDao;
import ar.ungs.infrastructure.data.entities.InspectionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InspectionPersistenceJpa implements InspectionPersistence {

    private InspectionDao inspectionDao;

    @Autowired
    public InspectionPersistenceJpa(InspectionDao inspectionDao) {
        this.inspectionDao = inspectionDao;
    }

    @Override
    public Optional<Inspection> save(Inspection inspection) {
        InspectionEntity entity = new InspectionEntity(inspection);
        Optional<InspectionEntity> target = Optional.ofNullable(inspectionDao.save(entity));
        return target.isPresent() ? target.map(InspectionEntity::toModel) : Optional.empty();
    }
}
