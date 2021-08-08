package ar.ungs;

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

    @Autowired
    public DatabaseSeeder(InspectorDao inspectorDao) {
        this.inspectorDao = inspectorDao;
    }

     public void seedDatabase() {
        LogManager.getLogger().log(Level.DEBUG, "SEEDDDD");
        loadDatabaseGraph();
        seedInspectors();
    }

    private void seedInspectors() {
        graph.getInspectors().forEach(entity -> {
            inspectorDao.save(entity);
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