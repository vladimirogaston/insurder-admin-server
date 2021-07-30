package ar.ungs.infrastructure.data.entities;

import ar.ungs.domain.models.shared.Vehicle;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class VehicleEntity {

    private String patent;

    private String brand;

    private long ownerDni;

    private String ownerPhone;

    private String location;

    public VehicleEntity(Vehicle vehicle) {
        BeanUtils.copyProperties(vehicle,this);
    }

    public Vehicle toModel() {
        Vehicle vehicle = Vehicle.builder().build();
        BeanUtils.copyProperties(this, vehicle);
        return vehicle;
    }
}
