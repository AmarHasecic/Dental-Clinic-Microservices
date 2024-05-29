package ba.unsa.etf.nwt.service;

import ba.unsa.etf.nwt.model.DentistEntity;
import ba.unsa.etf.nwt.model.PatientEntity;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {
    private final AmqpTemplate rabbitTemplate;
    public RabbitMQSender(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(Object o, String routingKey) {
        rabbitTemplate.convertAndSend("direct-exchange", routingKey, o);
        System.out.println("Send msg = " + o);
    }

    public void sendPatient(PatientEntity patientEntity) {
        rabbitTemplate.convertAndSend("direct-exchange", "patient", patientEntity);
        System.out.println("Send msg = " + patientEntity);
    }

    public void sendDentist(DentistEntity dentistEntity) {
        rabbitTemplate.convertAndSend("direct-exchange", "dentist", dentistEntity);
        System.out.println("Send msg = " + dentistEntity);
    }
}
