package ar.ungs.infrastructure.api.resources;

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
    Schedule readOpenScheduleByInspectorCode(@PathVariable int inspectorCode) {
        return scheduleService.readNotNotifiedByInspector(inspectorCode);
    }

    @PutMapping(value = ID + INSPECTIONS + ID)
    public void registerComponent(@PathVariable int scheduleId,
                                  @PathVariable int inspectionId,
                                  @RequestBody @Valid Component component) {
        scheduleService.registerComponent(scheduleId, inspectionId, component);
    }

    @PatchMapping(value = ID + INSPECTIONS + ID)
    public void cancel(@PathVariable int scheduleId,
                       @PathVariable int inspectionId,
                       @RequestBody @Valid Cancellation cancellation) {
        scheduleService.cancel(scheduleId, inspectionId, cancellation);
    }

    @PatchMapping(value = ID + INSPECTIONS)
    public void close(@PathVariable int scheduleId, @RequestParam int inspectionId) {
        scheduleService.close(scheduleId, inspectionId);
    }

    @PatchMapping(value = ID)
    public void notifySchedule(@PathVariable int scheduleId){
        scheduleService.notifySchedule(scheduleId);
    }
}