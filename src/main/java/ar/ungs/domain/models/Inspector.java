package ar.ungs.domain.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Inspector {

    private int id;
    private boolean available;
}
