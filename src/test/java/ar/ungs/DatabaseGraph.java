package ar.ungs;

import ar.ungs.infrastructure.data.entities.InspectorEntity;
import lombok.Data;
import org.springframework.context.annotation.Profile;

import java.util.LinkedList;
import java.util.List;

@Profile("qa")
@Data
public class DatabaseGraph {

    private List<InspectorEntity> inspectors;

    public DatabaseGraph() {
        setInspectors(new LinkedList<>());
    }
}