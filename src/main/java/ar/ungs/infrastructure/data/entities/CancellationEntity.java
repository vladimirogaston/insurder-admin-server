package ar.ungs.infrastructure.data.entities;

import ar.ungs.domain.models.shared.Cancellation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Date;

@Data
@EqualsAndHashCode
@Embeddable
public class CancellationEntity {

    private Date registration;
    private String description;

    public CancellationEntity() {
        setDescription("");
        setRegistration(new Date());
    }

    public CancellationEntity(Cancellation cancellation) {
        BeanUtils.copyProperties(cancellation,this);
    }

    public Cancellation toModel() {
        Cancellation cancellation = Cancellation.builder().build();
        BeanUtils.copyProperties(this,cancellation);
        return cancellation;
    }
}
