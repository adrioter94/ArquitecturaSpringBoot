package com.scalian.ArquitecturaSpringBoot.kafka;

import com.scalian.ArquitecturaSpringBoot.model.events.LibroEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LibroEventConsumer {

    @KafkaListener(
            topics = "${app.kafka.topic}",
            groupId = "analytics-service"
    )
    public void listen(LibroEvent event, Acknowledgment ack) {

        log.info("📥 Recibido evento: {}", event);

        try {
            procesarEvento(event);

            // Commit manual SOLO si todo va bien
            ack.acknowledge();
            log.info("✅ ACK realizado para libro {}", event.getLibroId());

        } catch (Exception e) {
            log.error("❌ ERROR procesando libro {} - NO se hace ACK", event.getLibroId(), e);
            // No se hace acknowledge → se reintentará
            throw e;
        }
    }

    private void procesarEvento(LibroEvent event) {
        // Simular fallo a propósito
        if (event.getLibroId() == 100) {
            throw new RuntimeException("Fallo simulado procesando libro 100");
        }

        // Procesamiento normal
        log.info("📊 Procesado correctamente libro {}", event.getLibroId());
    }
}
