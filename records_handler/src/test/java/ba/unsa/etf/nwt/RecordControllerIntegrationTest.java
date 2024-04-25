package ba.unsa.etf.nwt;

import ba.unsa.etf.nwt.dto.RecordRequestDto;
import ba.unsa.etf.nwt.dto.RecordResponseDto;
import ba.unsa.etf.nwt.model.AppointmentEntity;
import ba.unsa.etf.nwt.model.PatientEntity;
import ba.unsa.etf.nwt.model.RecordEntity;
import ba.unsa.etf.nwt.repository.AppointmentsRepository;
import ba.unsa.etf.nwt.repository.PatientsRepository;
import ba.unsa.etf.nwt.repository.RecordsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Assert;
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
import org.springframework.test.web.servlet.MvcResult;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void populateBase() {
        PatientEntity patient = new PatientEntity();
        patient.setId(1);
        patient.setAddress("Address");
        patient.setGender("Male");
        patient.setPhone("38762423658");
        patient.setBirthDate(new Date());
        patientsRepository.save(patient);

        PatientEntity patient2 = new PatientEntity();
        patient2.setId(2);
        patient2.setAddress("Address");
        patient2.setGender("Male");
        patient2.setPhone("38762423658");
        patient2.setBirthDate(new Date());
        patientsRepository.save(patient2);

        AppointmentEntity appointment = new AppointmentEntity();
        appointment.setId(1);
        appointment.setPatient(patient);
        appointment.setDate(new Date());
        appointment.setNote("Note");
        appointmentsRepository.save(appointment);

        AppointmentEntity appointment2 = new AppointmentEntity();
        appointment2.setId(2);
        appointment2.setPatient(patient2);
        appointment2.setDate(new Date());
        appointment2.setNote("Note");
        appointmentsRepository.save(appointment2);

        RecordEntity record = new RecordEntity();
        record.setId(1L);
        record.setAppointmentId(appointment);
        record.setPatientId(patient);
        record.setImage("src");
        recordsRepository.save(record);

        PatientEntity patient3 = new PatientEntity();
        patient3.setId(3);
        patient3.setAddress("Address");
        patient3.setGender("Male");
        patient3.setPhone("38762423658");
        patient3.setBirthDate(new Date());
        patientsRepository.save(patient3);

        AppointmentEntity appointment3 = new AppointmentEntity();
        appointment3.setId(3);
        appointment3.setPatient(patient3);
        appointment3.setDate(new Date());
        appointment3.setNote("Note");
        appointmentsRepository.save(appointment3);

    }

    @After
    public void emptyBase() {
        recordsRepository.deleteAll();
        appointmentsRepository.deleteAll();
        patientsRepository.deleteAll();
    }

    private RecordRequestDto createRecordDto() {
        RecordRequestDto recordRequestDto = new RecordRequestDto();
        recordRequestDto.setId(3L);
        recordRequestDto.setAppointmentId(3L);
        recordRequestDto.setPatientId(3L);
        recordRequestDto.setImage("url");
        return recordRequestDto;
    }

    private RecordRequestDto createRecordDto2() {
        RecordRequestDto recordRequestDto = new RecordRequestDto();
        recordRequestDto.setId(1L);
        recordRequestDto.setAppointmentId(1L);
        recordRequestDto.setPatientId(1L);
        recordRequestDto.setImage("url");
        return recordRequestDto;
    }

    private RecordRequestDto createInvalidRecordDto() {
        RecordRequestDto recordResponseDto = new RecordRequestDto();
        recordResponseDto.setAppointmentId(1000L);
        recordResponseDto.setPatientId(1L);
        recordResponseDto.setImage("url");
        return recordResponseDto;
    }

    private RecordRequestDto createInvalidRecordDto2() {
        RecordRequestDto recordResponseDto = new RecordRequestDto();
        recordResponseDto.setAppointmentId(1L);
        recordResponseDto.setPatientId(2L);
        recordResponseDto.setImage("url");
        return recordResponseDto;
    }

    @Test
    public void successfullyCreateRecord() throws Exception {
        RecordRequestDto recordRequestDto = createRecordDto();

        String json = objectMapper.writeValueAsString(recordRequestDto);

        mvc.perform(post("/records")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .characterEncoding("utf-8"))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void createRecordThrowsIllegalArgumentException() throws Exception {
        RecordRequestDto recordRequestDto = createInvalidRecordDto();

        String json = objectMapper.writeValueAsString(recordRequestDto);

        mvc.perform(post("/records")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .characterEncoding("utf-8"))
                .andExpect(status().isNotFound())
                .andReturn();

    }

    @Test
    public void createRecordThrowsEntityNotFoundException() throws Exception {
        RecordRequestDto recordRequestDto = createInvalidRecordDto2();

        String json = objectMapper.writeValueAsString(recordRequestDto);

        mvc.perform(post("/records")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .characterEncoding("utf-8"))
                .andExpect(status().isNotFound())
                .andReturn();

    }

    @Test
    public void successfullyGetPatientRecord() throws Exception {
        MvcResult response = mvc.perform(get("/records/1/1"))
                .andExpect(status().isOk())
                .andReturn();
        response.getResponse().getContentAsString();
        RecordResponseDto result = objectMapper.readValue(response.getResponse().getContentAsString(), RecordResponseDto.class);
        Assert.assertEquals(1, result.getPatient().getId());
        Assert.assertEquals(1, result.getAppointment().getId());
    }

    @Test
    public void getPatientRecordThrowsPaitientNotFound() throws Exception {
        mvc.perform(get("/records/1235/1"))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void getPatientRecordThrowsRecordNotFound() throws Exception {
        mvc.perform(get("/records/1/11235"))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void getPatientRecordThrowsRecordAndPatientIdNotMatch() throws Exception {
        mvc.perform(get("/records/1/2"))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void successfullyGetPatientRecords() throws Exception {
        mvc.perform(get("/records/1"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getAllPatientRecordThrowsPaitientNotFound() throws Exception {
        mvc.perform(get("/records/1235"))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void getAllPatientRecordReturnsNoContent() throws Exception {
        mvc.perform(get("/records/2"))
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    public void successfullyUpdateRecord() throws Exception {
        RecordRequestDto recordRequestDto = createRecordDto2();

        String json = objectMapper.writeValueAsString(recordRequestDto);

        mvc.perform(put("/records/1/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .characterEncoding("utf-8"))
                .andExpect(status().isCreated())
                .andReturn();
    }
}
