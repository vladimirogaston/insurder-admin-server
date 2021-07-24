package ar.ungs.domain.models.shared;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class Vehicle {

    @NotBlank
    private String patent;

    @NotBlank
    private String brand;

    @NotNull
    private long ownerDni;

    @NotBlank
    private String ownerPhone;

    @NotBlank
    private String location;
}
