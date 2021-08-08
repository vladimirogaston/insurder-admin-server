package ar.ungs;

import ar.ungs.infrastructure.data.daos.InspectorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("qa")
@Service
public class DatabaseSeeder {

    private static final String INSPECTORS_DS = "inspectors.yml";

    private InspectorDao inspectorDao;

    @Autowired
    public DatabaseSeeder(InspectorDao inspectorDao) {
        this.inspectorDao = inspectorDao;
    }

    public void seed() {
        loadGraph();
    }

    public void loadGraph() {
    }
}