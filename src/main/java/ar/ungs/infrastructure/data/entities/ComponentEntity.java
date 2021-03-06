package ar.ungs.infrastructure.data.entities;

import ar.ungs.domain.models.shared.AcceptanceLevel;
import ar.ungs.domain.models.shared.Component;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Data
@EqualsAndHashCode
@Entity
public class ComponentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String brand;

    private String code;

    @Enumerated(EnumType.STRING)
    private AcceptanceLevel acceptanceLevel;

    public ComponentEntity() {
        setId(-1);
        setBrand("");
        setCode("");
        setAcceptanceLevel(AcceptanceLevel.BAD);
    }

    public ComponentEntity(Component component) {
        BeanUtils.copyProperties(component,this);
    }

    public Component toModel() {
        Component component = Component.builder().build();
        BeanUtils.copyProperties(this,component);
        return component;
    }
}
