package org.example.event;

import lombok.RequiredArgsConstructor;
import org.example.entity.Item;
import org.example.entity.Movement;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class KafkaEventPublisher {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishPostalEvent(Item item, String eventType) {
        Map<String, Object> event = Map.of(
                "eventType", eventType,
                "itemId", item.getId(),
                "itemType", item.getType().name(),
                "status", item.getStatus().name(),
                "timestamp", Instant.now().toString()
        );
        kafkaTemplate.send("postal-events", event);
    }

    public void publishMovementEvent(Movement movement) {
        Map<String, Object> event = Map.of(
                "eventType", "MOVEMENT_" + movement.getType().name(),
                "itemId", movement.getPostalItem().getId(),
                "postOfficeId", movement.getPostOffice().getId(),
                "postOfficeName", movement.getPostOffice().getName(),
                "timestamp", movement.getEventTime().toString()
        );
        kafkaTemplate.send("movement-events", event);
    }
}