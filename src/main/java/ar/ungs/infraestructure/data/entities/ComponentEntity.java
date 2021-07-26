package ar.ungs.infraestructure.data.entities;

import ar.ungs.domain.models.shared.Condition;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class ComponentEntity {

    @Id
    @GeneratedValue
    private int id;

    @Enumerated
    private Condition condition;

    private String brand;

    private String code;
}
