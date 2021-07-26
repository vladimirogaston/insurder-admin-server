package ar.ungs.infraestructure.api.dtos;

import ar.ungs.domain.models.inspection.Inspection;
import ar.ungs.domain.models.shared.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InspectionCreationDto {

    @NotNull
    private Vehicle vehicle;

    @NotNull
    private Date preparationDate;

    public Inspection toModel() {
        Inspection inspection = new Inspection();
        BeanUtils.copyProperties(this, inspection);
        return inspection;
    }
}