package ar.ungs.infrastructure.data.persistence;

import ar.ungs.domain.models.inspection.Inspection;
import ar.ungs.domain.out_ports.InspectionPersistence;
import ar.ungs.infrastructure.data.daos.InspectionDao;
import ar.ungs.infrastructure.data.entities.InspectionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InspectionPersistenceJpa implements InspectionPersistence {

    private InspectionDao inspectionDao;

    @Autowired
    public InspectionPersistenceJpa(InspectionDao inspectionDao) {
        this.inspectionDao = inspectionDao;
    }

    @Override
    public void save(Inspection inspection) {
        inspectionDao.save(new InspectionEntity(inspection));
    }
}
