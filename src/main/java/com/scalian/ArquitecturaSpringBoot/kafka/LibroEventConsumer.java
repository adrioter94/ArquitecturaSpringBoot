package com.scalian.ArquitecturaSpringBoot.kafka;

import com.scalian.ArquitecturaSpringBoot.model.events.LibroEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LibroEventConsumer {

    @KafkaListener(
            topics = "${app.kafka.topic}",       // Se suscribe al topic 'libros'
            groupId = "analytics-service"
    )
    public void listen(LibroEvent event) {
        // Por ahora solo log
        log.info("AnalyticsService - recibido evento de libro {} con estado {}",
                event.getLibroId(), event.getStatus());
    }
}
