package ar.ungs.infrastructure.api.resources;

import ar.ungs.domain.in_ports.InspectionService;
import ar.ungs.infrastructure.api.dtos.InspectionCreationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public void save(@RequestBody @Valid InspectionCreationDto inspectionDto) {
        this.inspectionService.prepare(inspectionDto.toModel());
    }
}