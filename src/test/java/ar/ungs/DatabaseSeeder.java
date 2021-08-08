package ar.ungs;

import ar.ungs.infrastructure.data.daos.InspectionDao;
import ar.ungs.infrastructure.data.daos.InspectorDao;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.mapstruct.ValueMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;

@Service
public class DatabaseSeeder {

    @Value("${ymlFileName}")
    private String ymlFileName;
    private DatabaseGraph graph;
    private InspectorDao inspectorDao;
    private InspectionDao inspectionDao;

    @Autowired
    public DatabaseSeeder(InspectorDao inspectorDao, InspectionDao inspectionDao) {
        this.inspectorDao = inspectorDao;
        this.inspectionDao = inspectionDao;
    }

     public void seedDatabase() {
        loadDatabaseGraph();
        seedInspectors();
        seedInspections();
    }

    private void seedInspectors() {
        graph.getInspectors().forEach(entity -> {
            inspectorDao.save(entity);
            LogManager.getLogger().log(Level.INFO, "DB::SEED > " + entity.toString());
        });
    }

    private void seedInspections() {
        graph.getInspections().forEach(entity -> {
            inspectionDao.save(entity);
            LogManager.getLogger().log(Level.INFO, "DB::SEED > " + entity.toString());
        });
    }

    private void loadDatabaseGraph() {
        try {
            LogManager.getLogger(this.getClass()).log(Level.INFO,
                    "Seed database operation status: [INITIALIZED - LOADING .YML]");
            Yaml yaml = new Yaml(new Constructor(DatabaseGraph.class));
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(ymlFileName);
            graph = yaml.load(inputStream);
        } catch (Exception e) {
            LogManager.getLogger(this.getClass()).log(Level.ERROR,
                    "Seed database operation status: [ABORT - ERROR LOADING .YML, " + e.getMessage() + "]");
        }
    }
}