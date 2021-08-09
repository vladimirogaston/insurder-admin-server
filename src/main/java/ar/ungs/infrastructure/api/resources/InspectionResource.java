package ar.ungs.infrastructure.api.resources;

import ar.ungs.domain.exceptions.NotFoundException;
import ar.ungs.domain.in_ports.InspectionService;
import ar.ungs.domain.models.inspection.Inspection;
import ar.ungs.domain.models.shared.Vehicle;
import ar.ungs.infrastructure.api.dtos.InspectionCreationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping(value = InspectionResource.INSPECTIONS)
public class InspectionResource {

    public static final String INSPECTIONS = "/inspections";

    private InspectionService inspectionService;

    @Autowired
    public InspectionResource(InspectionService inspectionService) {
        this.inspectionService = inspectionService;
    }

    @PostMapping
    public Inspection save(@RequestBody @Valid InspectionCreationDto inspectionDto) {
        Inspection ret = this.inspectionService.save(inspectionDto.toModel()).orElseThrow(()->new NotFoundException(""));
        return ret;
    }
}