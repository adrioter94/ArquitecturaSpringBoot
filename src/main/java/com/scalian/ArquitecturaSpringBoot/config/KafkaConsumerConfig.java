package com.scalian.ArquitecturaSpringBoot.config;

import com.scalian.ArquitecturaSpringBoot.model.events.LibroEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.util.backoff.FixedBackOff;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<String, LibroEvent> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9094");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "analytics-service");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        JsonDeserializer<LibroEvent> deserializer = new JsonDeserializer<>(LibroEvent.class);
        deserializer.addTrustedPackages("*");

        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                deserializer
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, LibroEvent> kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, LibroEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory());

        // commit manual del offset
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);

        // desactivar reintentos automáticos
        DefaultErrorHandler errorHandler = new DefaultErrorHandler(
                (record, exception) -> {}, // no hacer nada
                new FixedBackOff(0L, 0L) // 0 reintentos
        );

        errorHandler.setAckAfterHandle(false); // súper importante

        factory.setCommonErrorHandler(errorHandler);

        return factory;
    }
}
