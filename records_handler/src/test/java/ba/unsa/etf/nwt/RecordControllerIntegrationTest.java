package ba.unsa.etf.nwt;

import ba.unsa.etf.nwt.dto.RecordDto;
import ba.unsa.etf.nwt.model.AppointmentEntity;
import ba.unsa.etf.nwt.model.PatientEntity;
import ba.unsa.etf.nwt.repository.AppointmentsRepository;
import ba.unsa.etf.nwt.repository.PatientsRepository;
import ba.unsa.etf.nwt.repository.RecordsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = RecordsHandlerApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class RecordControllerIntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private RecordsRepository recordsRepository;

    @Autowired
    private PatientsRepository patientsRepository;

    @Autowired
    private AppointmentsRepository appointmentsRepository;

    @Test
    public void successfullyCreateRecord() throws Exception {
        PatientEntity patient = new PatientEntity();
        patient.setId(1);
        patient.setAddress("Address");
        patient.setGender("Male");
        patient.setPhone("38762423658");
        patient.setBirthDate(new Date());
        patientsRepository.save(patient);

        AppointmentEntity appointment = new AppointmentEntity();
        appointment.setId(1);
        appointment.setPatient(patient);
        appointment.setDate(new Date());
        appointment.setNote("Note");
        appointmentsRepository.save(appointment);

        RecordDto recordDto = new RecordDto();
        recordDto.setAppointment(appointment);
        recordDto.setPatient(patient);
        recordDto.setImage("url");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(recordDto);

        mvc.perform(post("/records")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8"))
                .andExpect(status().isCreated())
                .andReturn();
    }
}
