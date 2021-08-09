package ar.ungs.domain.in_ports;

import ar.ungs.domain.models.inspection.Inspection;

import java.util.Optional;

public interface InspectionService {

    Optional<Inspection> save(Inspection inspection);
}
