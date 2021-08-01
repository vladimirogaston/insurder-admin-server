package ar.ungs.infrastructure.api.dtos;

import ar.ungs.domain.models.shared.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

class InspectionCreationDtoTest {


    private InspectionCreationDto dto;

    @BeforeEach
    void setUp() {
        dto = new InspectionCreationDto();
        dto.setPreparationDate(new Date());
        dto.setVehicle(Vehicle.builder().build());
    }

    @Test
    void toModel() {
    }
}