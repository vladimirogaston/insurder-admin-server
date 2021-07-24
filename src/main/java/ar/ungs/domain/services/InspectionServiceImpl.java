package ar.ungs.domain.services;

import ar.ungs.domain.models.inspection.Inspection;
import ar.ungs.domain.out_ports.InspectionPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InspectionServiceImpl {

    private InspectionPersistence inspectionPersistence;

    @Autowired
    public InspectionServiceImpl(InspectionPersistence inspectionPersistence) {
        this.inspectionPersistence = inspectionPersistence;
    }

    public void prepare(Inspection inspection) {
        this.inspectionPersistence.save(inspection);
    }
}
