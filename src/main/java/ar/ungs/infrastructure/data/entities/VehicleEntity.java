package ar.ungs.infrastructure.data.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@Embeddable
public class VehicleEntity {

    private String patent;

    private String brand;

    private long ownerDni;

    private String ownerPhone;

    private String location;
}
