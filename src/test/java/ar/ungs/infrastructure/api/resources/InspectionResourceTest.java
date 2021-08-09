package ar.ungs.infrastructure.api.resources;

import ar.ungs.domain.in_ports.InspectionService;
import ar.ungs.domain.models.inspection.Inspection;
import ar.ungs.domain.models.shared.Vehicle;
import ar.ungs.infrastructure.api.dtos.InspectionCreationDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {InspectionResource.class})
class InspectionResourceTest {

    @MockBean
    InspectionService inspectionService;

    @Autowired
    MockMvc mockMvc;

    final String URI = "/inspections";
    InspectionCreationDto INSPECTION_CREATION_DTO;

    @BeforeEach
    void setUp() {
        INSPECTION_CREATION_DTO = new InspectionCreationDto();
        INSPECTION_CREATION_DTO.setPreparationDate(new Date());
        INSPECTION_CREATION_DTO.setVehicle(Vehicle.builder()
                .brand("Fiat")
                .patent("TCC-789")
                .location("Route 666")
                .ownerDni(123321)
                .ownerPhone("1146634657")
                .build());
    }

    @Test
    void save() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(INSPECTION_CREATION_DTO))
                .contentType(MediaType.APPLICATION_JSON);

        Inspection target = INSPECTION_CREATION_DTO.toModel();
        Mockito.when(inspectionService.prepare(target)).thenReturn(java.util.Optional.ofNullable(target));

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk()).andReturn();
    }
}