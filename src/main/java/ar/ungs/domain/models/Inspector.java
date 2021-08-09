package ar.ungs.domain.models;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode
public class Inspector {

    private Integer id;
    private boolean available;
}
