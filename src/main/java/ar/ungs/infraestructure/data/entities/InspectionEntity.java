package ar.ungs.infraestructure.data.entities;

import ar.ungs.domain.models.inspection.Inspection;
import ar.ungs.domain.models.shared.State;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class InspectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Embedded
    private VehicleEntity vehicle;

    @OneToMany
    private List<ComponentEntity> components;

    @Embedded
    private CancellationEntity cancellation;

    private Date preparationDate;

    private Date planingDate;

    private String scheduleCode;

    private Date closingDate;

    @Enumerated(value = EnumType.STRING)
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