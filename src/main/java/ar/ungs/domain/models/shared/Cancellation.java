package ar.ungs.domain.models.shared;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
public class Cancellation {

    @NotNull
    private Date registration;

    @NotBlank
    private String description;
}
