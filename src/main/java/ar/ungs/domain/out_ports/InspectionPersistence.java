package ar.ungs.domain.out_ports;

import ar.ungs.domain.models.inspection.Inspection;

import java.util.Optional;

public interface InspectionPersistence {

    Optional<Inspection> save(Inspection inspection);
}
