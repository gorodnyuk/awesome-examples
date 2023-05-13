package pro.gorodnyuk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumerService {

    @KafkaListener(topics = "${kafka.topicName}", groupId = "group_id", containerFactory = "concurrentKafkaListenerContainerFactory")
    public User consume(User user) {
        log.info("Message was received: {}", user.toString());
        return user;
    }
}
