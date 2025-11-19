package com.scalian.ArquitecturaSpringBoot.kafka;

import com.scalian.ArquitecturaSpringBoot.model.events.LibroEvent;
import com.scalian.ArquitecturaSpringBoot.model.entity.Libro;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class LibroEventProducer {

    private final KafkaTemplate<String, LibroEvent> kafkaTemplate;

    @Value("${app.kafka.topic}")
    private String topicName;

    public void sendLibroCreated(Libro libro) {
        LibroEvent event = new LibroEvent(
                libro.getId(),
                "CREATED",
                Instant.now()
        );
        kafkaTemplate.send(topicName, libro.getId().toString(), event);
    }

    public void sendLibroUpdated(Libro libro) {
        LibroEvent event = new LibroEvent(
                libro.getId(),
                "UPDATED",
                Instant.now()
        );
        kafkaTemplate.send(topicName, libro.getId().toString(), event);
    }
}
