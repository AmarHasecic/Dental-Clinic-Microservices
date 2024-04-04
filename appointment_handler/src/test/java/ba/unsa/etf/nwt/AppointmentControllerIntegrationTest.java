package ba.unsa.etf.nwt;

import ba.unsa.etf.nwt.dto.AppointmentDto;
import ba.unsa.etf.nwt.model.AppointmentEntity;
import ba.unsa.etf.nwt.model.DentistEntity;
import ba.unsa.etf.nwt.model.PatientEntity;
import ba.unsa.etf.nwt.model.ServiceEntity;
import ba.unsa.etf.nwt.repository.AppointmentsRepository;
import ba.unsa.etf.nwt.repository.DentistsRepository;
import ba.unsa.etf.nwt.repository.PatientsRepository;
import ba.unsa.etf.nwt.repository.ServicesRepository;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class AppointmentControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PatientsRepository patientsRepository;

    @Autowired
    private AppointmentsRepository appointmentsRepository;

    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    private DentistsRepository dentistsRepository;

    PatientEntity patient = new PatientEntity(2,"Address",new Date(),"0336458795","Male");
    ServiceEntity  service = new ServiceEntity(2,"Pregled","Redovna usluga", 60);
    DentistEntity dentist = new DentistEntity(2,"8:00 - 16:00");
    AppointmentEntity appointment = new AppointmentEntity(2,new Date(),dentist,patient,service,"Redovni pregled");

    @Before
    public void addTestDataToDatabase(){

        patientsRepository.save(patient);
        servicesRepository.save(service);
        dentistsRepository.save(dentist);
        appointmentsRepository.save(appointment);

    }
    @After
    public void cleanDatabase(){
        appointmentsRepository.delete(appointment);
        patientsRepository.delete(patient);
        servicesRepository.delete(service);
        dentistsRepository.delete(dentist);
    }


    @Test
    public void createAppointmentTest() throws Exception {

        PatientEntity patient = new PatientEntity(2,"Address",new Date(),"0336458795","Male");
        ServiceEntity  service = new ServiceEntity(2,"Pregled","Redovna usluga", 60);
        DentistEntity dentist = new DentistEntity(2,"8:00 - 16:00");

        AppointmentDto appointmentDto = new AppointmentDto(2L,dentist,patient,service,"Redovni pregled");


        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(appointmentDto);

        mvc.perform(post("/appointments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .characterEncoding("utf-8"))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void getAllAppointmentsTest() throws Exception {
        mvc.perform(get("/appointments"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getAppintmentWitIdTest() throws Exception {
        mvc.perform(get("/appointments/2"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getAppintmentWitIdNotExistsTest() throws Exception {
        mvc.perform(get("/appointments/20"))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void updateAppintmentWitIdTest() throws Exception {

        PatientEntity patient = new PatientEntity(2,"Address",new Date(),"0336458795","Male");
        ServiceEntity  service = new ServiceEntity(2,"Pregled","Redovna usluga", 60);
        DentistEntity dentist = new DentistEntity(2,"8:00 - 17:00");
        AppointmentDto appointmentDto = new AppointmentDto(2L,dentist,patient,service,"Redovni pregled");


        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(appointmentDto);
        mvc.perform(put("/appointments/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();
    }
}


/*
///////// POSSIBLE FUTURE USAGE //////////////
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AppointmentControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Mock
    private PatientsRepository patientsRepository;

    @Mock
    private AppointmentsRepository appointmentsRepository;

    @Mock
    private ServicesRepository servicesRepository;

    @Mock
    private DentistsRepository dentistsRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        PatientEntity patient = new PatientEntity(2, "Address", new Date(), "0336458795", "Male");
        ServiceEntity service = new ServiceEntity(2, "Pregled", "Redovna usluga", 60);
        DentistEntity dentist = new DentistEntity(2, "8:00 - 16:00");
        AppointmentEntity appointmentEntity = new AppointmentEntity(2L, new Date(), dentist, patient, service, "Redovni pregled");

        when(dentistsRepository.findById(2L)).thenReturn(java.util.Optional.of(dentist));
        when(appointmentsRepository.save(any(AppointmentEntity.class))).thenReturn(appointmentEntity);
        when(appointmentsRepository.findById(2L)).thenReturn(Optional.of(appointmentEntity));
    }


    @Test
    public void getAllAppointmentsTest() throws Exception {
        mvc.perform(get("/appointments"))
                .andExpect(status().isOk())
                .andReturn();
    }
}
*/
