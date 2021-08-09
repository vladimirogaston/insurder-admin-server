package ar.ungs.infrastructure.data.entities;

import ar.ungs.domain.models.inspection.Inspection;
import ar.ungs.domain.models.shared.Component;
import ar.ungs.domain.models.shared.State;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class InspectionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ComponentEntity> components;

    private Date preparationDate;

    private Date planingDate;

    private Integer scheduleCode;

    private Date closingDate;

    @Basic(optional = true)
    @Embedded
    private CancellationEntity cancellation;

    @Basic(optional = false)
    @Embedded
    private VehicleEntity vehicle;

    @Enumerated(value = EnumType.STRING)
    private State currentState;

    public InspectionEntity(Inspection inspection) {
        BeanUtils.copyProperties(inspection, this);
        if(inspection.getVehicle() != null) setVehicle(new VehicleEntity(inspection.getVehicle()));
        if(inspection.getCancellation() != null) setCancellation(new CancellationEntity(inspection.getCancellation()));
        this.components = new LinkedList<>();
        inspection.getComponents().forEach(c->{
            components.add(new ComponentEntity(c));
        });
    }

    public Inspection toModel() {
        Inspection inspection = new Inspection();
        BeanUtils.copyProperties(this, inspection);
        LinkedList<Component> components = new LinkedList<>();
        getComponents().forEach(componentEntity -> {
            components.add(componentEntity.toModel());
        });
        inspection.setComponents(components);
        if(vehicle != null) inspection.setVehicle(vehicle.toModel());
        if(cancellation != null) inspection.setCancellation(cancellation.toModel());
        return inspection;
    }
}