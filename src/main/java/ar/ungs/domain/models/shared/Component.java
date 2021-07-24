package ar.ungs.domain.models.shared;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class Component {

    public enum Condition { BAD, REGULAR, GOOD, UNREDEABLE };

    @NotNull
    private Condition condition;

    @NotBlank
    private String brand;

    @NotBlank
    @Digits(integer = 10, fraction = 0)
    private String code;
}
