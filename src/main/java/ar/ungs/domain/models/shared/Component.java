package ar.ungs.domain.models.shared;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@EqualsAndHashCode
public class Component {

    @NotNull
    private AcceptanceLevel acceptanceLevel;

    @NotBlank
    private String brand;

    @NotBlank
    @Digits(integer = 10, fraction = 0)
    private String code;
}
