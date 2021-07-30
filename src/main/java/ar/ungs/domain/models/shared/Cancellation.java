package ar.ungs.domain.models.shared;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@EqualsAndHashCode
public class Cancellation {

    @NotNull
    private Date registration;

    @NotBlank
    private String description;
}
