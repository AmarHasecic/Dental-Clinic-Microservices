package ba.unsa.etf.nwt.rabbitmq;

import ba.unsa.etf.nwt.model.DentistEntity;
import ba.unsa.etf.nwt.model.PatientEntity;
import ba.unsa.etf.nwt.repository.DentistsRepository;
import ba.unsa.etf.nwt.repository.PatientsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListener implements MessageListener {

    private final DentistsRepository dentistsRepository;

    private final PatientsRepository patientsRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    public RabbitMQListener(DentistsRepository dentistsRepository, PatientsRepository patientsRepository) {
        this.dentistsRepository = dentistsRepository;
        this.patientsRepository = patientsRepository;
    }

    @Override
    public void onMessage(Message message) {
        String jsonMessage = new String(message.getBody());
        if (jsonMessage.contains("workHours")) {
            try {
                DentistEntity dentistEntity = objectMapper.readValue(jsonMessage, DentistEntity.class);
                dentistEntity = dentistsRepository.save(dentistEntity);
                System.out.println(dentistEntity);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                PatientEntity patientEntity = objectMapper.readValue(jsonMessage, PatientEntity.class);
                patientEntity = patientsRepository.save(patientEntity);
                System.out.println(patientEntity);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}