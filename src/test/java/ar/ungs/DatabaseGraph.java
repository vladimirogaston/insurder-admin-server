package ar.ungs;

import ar.ungs.infrastructure.data.entities.InspectorEntity;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class DatabaseGraph {

    private List<InspectorEntity> inspectors;

    public DatabaseGraph() {
        setInspectors(new LinkedList<>());
    }
}