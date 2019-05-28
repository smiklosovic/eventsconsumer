package eventsconsumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import eventsconsumer.repository.EventsCassandraRepository;
import eventsconsumer.repository.SensorEvents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EventsConsumer {

    private static ObjectReader objectReader = new ObjectMapper().readerFor(SensorEvents.class);

    @Autowired
    private EventsCassandraRepository repository;

    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.consumerGroup}", containerFactory = "kafkaListenerContainerFactory")
    public void listen(String message) throws IOException {
        System.out.println("Received Message: " + message);
        SensorEvents sensorEvents = objectReader.readValue(message);
        repository.insert(sensorEvents);
    }
}
