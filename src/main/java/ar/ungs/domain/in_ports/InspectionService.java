package ar.ungs.domain.in_ports;

import ar.ungs.domain.models.inspection.Inspection;

public interface InspectionService {

    void prepare(Inspection inspection);
}
