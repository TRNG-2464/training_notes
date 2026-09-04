package com.revature.springkafkademo.config;

import lombok.AllArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.kafka.autoconfigure.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;

/*
 * This Configuration class is required for this application
 * because it has Serialization & Deserialization settings to
 * support both String and JSON formats.
 *
 * In an application that only supports 1, default configuration
 * settings can be used in an application.properties/application.yml
 */
@Configuration
@AllArgsConstructor
public class GreetingKafkaConfig {

    /*
     * The KafkaProperties object contains default configuration details
     * as detailed in the application.properties/application.yml file.
     */
    private final KafkaProperties kafkaProperties;

    @Bean
    public ProducerFactory<String, String> greetingProducerFactory() {
        // Import the default settings...
        Map<String, Object> configProps = kafkaProperties.buildProducerProperties();
        /*
         * These come from application.properties already (String serializers),
         * but included here explicitly for clarity. i.e. I don't actually
         * need to set any values here, but I am doing so just to showcase
         * how it looks (see JSON configuration in the 'OrderKafkaConfig' for details
         */
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    /*
     * This bean will be used by the GreetingProducer so that it understands
     * how it will read event data (as a String)
     */
    @Bean
    public KafkaTemplate<String, String> greetingKafkaTemplate() {
        return new KafkaTemplate<>(greetingProducerFactory());
    }
}
