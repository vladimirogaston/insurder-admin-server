package ar.ungs.infrastructure.api.resources;

import ar.ungs.domain.in_ports.ScheduleService;
import ar.ungs.domain.models.Inspector;
import ar.ungs.domain.models.Schedule;
import ar.ungs.domain.models.inspection.Inspection;
import ar.ungs.domain.models.shared.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {ScheduleResource.class})
class ScheduleResourceTest {

    public static final String SCHEDULES_ID = "/schedules/{id}";
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ScheduleService scheduleService;

    Schedule target;

    @BeforeEach
    void setUp() {
        target = new Schedule(Inspector.builder().id(10).available(true).build());
        target.setNotified(false);
        Inspection inspection = new Inspection();
        inspection.prepare(Vehicle.builder().build());
        target.plan(inspection);
        Mockito.when(scheduleService.readNotNotifiedByInspector(10)).thenReturn(java.util.Optional.of(target));
        Mockito.when(scheduleService.readNotNotifiedByInspector(1)).thenReturn(java.util.Optional.empty());
    }

    @Test
    void readOpenScheduleByInspectorCode() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get(SCHEDULES_ID, 10)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8");
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.inspector.id").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.notified").value(false))
                .andDo(print())
                .andReturn();
    }

    @Test
    void readWithNotExistentInspectorId() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get(SCHEDULES_ID, 1)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8");
        mockMvc.perform(request)
                .andExpect(status().is(404)).andReturn();
    }
}