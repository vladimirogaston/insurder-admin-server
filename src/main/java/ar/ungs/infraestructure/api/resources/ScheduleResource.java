package ar.ungs.infraestructure.api.resources;

import ar.ungs.domain.in_ports.ScheduleService;
import ar.ungs.domain.models.Schedule;
import ar.ungs.domain.models.shared.Cancellation;
import ar.ungs.domain.models.shared.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = ScheduleResource.SCHEDULES)
public class ScheduleResource {

    public static final String SCHEDULES = "/schedules";

    public static final String ID = "/{id}";

    public static final String INSPECTIONS = "/inspections";

    private ScheduleService scheduleService;

    @Autowired
    public ScheduleResource(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping(value = ID)
    Schedule readOpenScheduleByInspectorCode(@PathVariable String inspectorCode) {
        return scheduleService.readNotNotifiedByInspector(inspectorCode);
    }

    @PutMapping(value = ID + INSPECTIONS + ID)
    public void registerComponent(@PathVariable String scheduleId,
                                  @PathVariable String inspectionId,
                                  @RequestBody @Valid Component component) {
        scheduleService.registerComponent(scheduleId, inspectionId, component);
    }

    /**
     * @path: /schedules/{id}/inspections/{id}
     * @variable scheduleId
     * @variable inspectionId
     * @body cancellation
     */
    @PatchMapping(value = ID + INSPECTIONS + ID)
    public void cancel(@PathVariable String scheduleId,
                       @PathVariable String inspectionId,
                       @RequestBody @Valid Cancellation cancellation) {
        scheduleService.cancel(scheduleId, inspectionId, cancellation);
    }

    @PatchMapping(value = ID + INSPECTIONS)
    public void close(@PathVariable String scheduleId, @RequestParam String inspectionId) {
        scheduleService.close(scheduleId, inspectionId);
    }

    @PatchMapping(value = ID)
    public void notifySchedule(@PathVariable String scheduleId){
        scheduleService.notifySchedule(scheduleId);
    }
}