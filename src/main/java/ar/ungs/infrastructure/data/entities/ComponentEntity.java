package ar.ungs.infrastructure.data.entities;

import ar.ungs.domain.models.shared.AcceptanceLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class ComponentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String brand;

    private String serial;

    @Enumerated(EnumType.STRING)
    private AcceptanceLevel acceptanceLevel;
}
