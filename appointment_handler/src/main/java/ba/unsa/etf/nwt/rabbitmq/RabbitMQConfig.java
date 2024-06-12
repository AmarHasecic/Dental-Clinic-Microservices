package ba.unsa.etf.nwt.rabbitmq;

import ba.unsa.etf.nwt.repository.DentistsRepository;
import ba.unsa.etf.nwt.repository.PatientsRepository;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    private final DentistsRepository dentistsRepository;
    private final PatientsRepository patientsRepository;

    public RabbitMQConfig(DentistsRepository dentistsRepository, PatientsRepository patientsRepository) {
        this.dentistsRepository = dentistsRepository;
        this.patientsRepository = patientsRepository;
    }

    @Bean
    Queue dentistQueue() {
        return new Queue("dentistQueue", false);
    }

    @Bean
    Queue patientQueue() {
        return new Queue("patientQueue", false);
    }

    @Bean
    MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
        simpleMessageListenerContainer.setQueues(patientQueue(),dentistQueue());
        simpleMessageListenerContainer.setMessageListener(new RabbitMQListener(dentistsRepository,patientsRepository));
        return simpleMessageListenerContainer;
    }

}