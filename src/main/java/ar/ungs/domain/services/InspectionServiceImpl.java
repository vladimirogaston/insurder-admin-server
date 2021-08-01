package ar.ungs.domain.services;

import ar.ungs.domain.in_ports.InspectionService;
import ar.ungs.domain.models.inspection.Inspection;
import ar.ungs.domain.out_ports.InspectionPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InspectionServiceImpl implements InspectionService {

    private InspectionPersistence inspectionPersistence;

    @Autowired
    public InspectionServiceImpl(InspectionPersistence inspectionPersistence) {
        this.inspectionPersistence = inspectionPersistence;
    }

    @Override
    public void prepare(Inspection inspection) {
        if(inspection == null) throw new IllegalArgumentException("can't save inspection: is null.");
        this.inspectionPersistence.save(inspection);
    }
}