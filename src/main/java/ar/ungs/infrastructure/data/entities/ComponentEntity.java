package ar.ungs.infrastructure.data.entities;

import ar.ungs.domain.models.shared.AcceptanceLevel;
import ar.ungs.domain.models.shared.Component;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

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

    public ComponentEntity(Component component) {
        BeanUtils.copyProperties(component,this);
    }

    public Component toModel() {
        Component component = Component.builder().build();
        BeanUtils.copyProperties(this,component);
        return component;
    }
}
