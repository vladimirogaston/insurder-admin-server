package ar.ungs.domain.out_ports;

import ar.ungs.domain.models.inspection.Inspection;

public interface InspectionPersistence {

    void save(Inspection inspection);
}
