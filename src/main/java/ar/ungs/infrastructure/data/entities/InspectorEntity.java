package ar.ungs.infrastructure.data.entities;

import ar.ungs.domain.models.Inspector;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class InspectorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private boolean available;

    public InspectorEntity(Inspector inspector) {
        BeanUtils.copyProperties(inspector,this);
    }

    public Inspector toModel() {
        Inspector inspector = Inspector.builder().build();
        BeanUtils.copyProperties(this, inspector);
        return inspector;
    }
}
