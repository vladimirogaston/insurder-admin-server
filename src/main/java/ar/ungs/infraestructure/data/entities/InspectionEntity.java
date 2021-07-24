package ar.ungs.infraestructure.data.entities;

import ar.ungs.domain.models.inspection.Inspection;
import ar.ungs.domain.models.shared.Cancellation;
import ar.ungs.domain.models.shared.Component;
import ar.ungs.domain.models.shared.State;
import ar.ungs.domain.models.shared.Vehicle;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Document
public class InspectionEntity {
    @Id
    private String id;
    private Vehicle vehicle;
    private List<Component> components;
    private Cancellation cancellation;
    private Date preparationDate;
    private Date planingDate;
    private String scheduleCode;
    private Date closingDate;
    private State currentState;

    public InspectionEntity(Inspection inspection) {
        BeanUtils.copyProperties(inspection, this);
    }

    public Inspection toModel() {
        Inspection inspection = new Inspection();
        BeanUtils.copyProperties(this, inspection);
        return inspection;
    }
}
