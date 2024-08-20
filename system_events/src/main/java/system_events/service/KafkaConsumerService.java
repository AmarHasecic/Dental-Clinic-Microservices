package system_events.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "logs", groupId = "all_events_group")
    public void listenGroupLogs(String message) {
        System.out.println("Received message in group logs: " + message);
    }
}
