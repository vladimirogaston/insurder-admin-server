package ar.ungs.infraestructure.data.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.Date;

@Data
@NoArgsConstructor
@Embeddable
public class CancellationEntity {

    private Date registration;

    private String description;
}
