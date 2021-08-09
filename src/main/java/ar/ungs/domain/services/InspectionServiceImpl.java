package ar.ungs.domain.services;

import ar.ungs.domain.in_ports.InspectionService;
import ar.ungs.domain.models.inspection.Inspection;
import ar.ungs.domain.models.shared.State;
import ar.ungs.domain.out_ports.InspectionPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InspectionServiceImpl implements InspectionService {

    private InspectionPersistence inspectionPersistence;

    @Autowired
    public InspectionServiceImpl(InspectionPersistence inspectionPersistence) {
        this.inspectionPersistence = inspectionPersistence;
    }

    @Override
    public Optional<Inspection> prepare(Inspection inspection) {
        if(inspection == null) throw new IllegalArgumentException("can't save inspection: is null.");
        if(inspection.getCurrentState() != State.PLANNED) throw new IllegalArgumentException("can't save not a prepared inspection");
        return this.inspectionPersistence.save(inspection);
    }
}