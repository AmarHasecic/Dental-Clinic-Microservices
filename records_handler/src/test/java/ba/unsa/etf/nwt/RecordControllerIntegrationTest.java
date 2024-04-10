package ba.unsa.etf.nwt;

import ba.unsa.etf.nwt.dto.RecordResponseDto;
import ba.unsa.etf.nwt.model.AppointmentEntity;
import ba.unsa.etf.nwt.model.PatientEntity;
import ba.unsa.etf.nwt.model.RecordEntity;
import ba.unsa.etf.nwt.repository.AppointmentsRepository;
import ba.unsa.etf.nwt.repository.PatientsRepository;
import ba.unsa.etf.nwt.repository.RecordsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @Before
    public void populateBase() {
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

        appointment = new AppointmentEntity();
        appointment.setId(2);
        appointment.setPatient(patient);
        appointment.setDate(new Date());
        appointment.setNote("Note");
        appointmentsRepository.save(appointment);

        RecordEntity record = new RecordEntity();
        record.setId(1L);
        record.setAppointment(appointment);
        record.setPatient(patient);
        record.setImage("src");
        recordsRepository.save(record);

    }

    @After
    public void emptyBase() {
        recordsRepository.deleteAll();
        appointmentsRepository.deleteAll();
        patientsRepository.deleteAll();
    }

    private RecordResponseDto createRecordDto() {
        PatientEntity patient = new PatientEntity();
        patient.setId(1);

        AppointmentEntity appointment = new AppointmentEntity();
        appointment.setId(1);

        RecordResponseDto recordResponseDto = new RecordResponseDto();
        recordResponseDto.setAppointment(appointment);
        recordResponseDto.setPatient(patient);
        recordResponseDto.setImage("url");
        return recordResponseDto;
    }

    @Test
    public void successfullyCreateRecord() throws Exception {
        RecordResponseDto recordResponseDto = createRecordDto();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(recordResponseDto);

        mvc.perform(post("/records")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .characterEncoding("utf-8"))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void successfullyGetPatientRecord() throws Exception {
        mvc.perform(get("/records/1/1"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void successfullyGetPatientRecords() throws Exception {
        mvc.perform(get("/records/1"))
                .andExpect(status().isOk())
                .andReturn();
    }
}
