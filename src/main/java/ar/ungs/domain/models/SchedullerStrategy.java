package ar.ungs.domain.models;

import ar.ungs.domain.models.inspection.Inspection;

import java.util.Queue;
import java.util.Set;

public interface SchedullerStrategy {

    Set<Schedule> distributeWorkOverAvailableInspectors(Queue<Inspector> inspectors, Queue<Inspection> inspections);
}
