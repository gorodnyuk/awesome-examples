package pro.gorodnyuk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, User> kafkaTemplate;

    public void send(String topic, User user) {
        log.info("Sending message: {}", user);
        kafkaTemplate.send(topic, user);
    }
}
