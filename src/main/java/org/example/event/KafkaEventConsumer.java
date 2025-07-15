package org.example.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaEventConsumer {
    @KafkaListener(topics = "postal-events", groupId = "tracking-group")
    public void handlePostalEvent(Map<String, Object> event) {
        log.info("Received postal event: {}", event);
    }

    @KafkaListener(topics = "movement-events", groupId = "tracking-group")
    public void handleMovementEvent(Map<String, Object> event) {
        log.info("Received movement event: {}", event);
    }
}