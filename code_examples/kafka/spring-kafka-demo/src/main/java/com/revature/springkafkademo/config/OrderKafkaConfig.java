package com.revature.springkafkademo.config;

import com.revature.springkafkademo.models.OrderRequest;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.boot.kafka.autoconfigure.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.JacksonJsonDeserializer;
import org.springframework.kafka.support.serializer.JacksonJsonSerializer;
import org.springframework.util.backoff.ExponentialBackOff;

import java.util.Map;

/*
 * This class includes configuration details for the 'orders' topic, including
 * the producer/consumer serialization settings, factories for consumption and
 * error handling logic.
 */
@Configuration
@AllArgsConstructor
public class OrderKafkaConfig {

    /*
     * The KafkaProperties object contains default configuration details
     * as detailed in the application.properties/application.yml file.
     */
    private final KafkaProperties kafkaProperties;

    /*
     * Consumer Factory configuration is required to match the serialization
     * method of the producer (JSON serialization/deserialization)
     *
     * The Consumer Factory includes general settings about how to build
     * a Consumer client
     */
    @Bean
    public ConsumerFactory<String, OrderRequest> orderConsumerFactory() {
        Map<String, Object> configProps = kafkaProperties.buildConsumerProperties();

        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "orders-processing-group");
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JacksonJsonDeserializer.class);
        configProps.put(JacksonJsonDeserializer.TRUSTED_PACKAGES, "com.revature.springkafkademo.models");
        return new DefaultKafkaConsumerFactory<>(configProps);
    }

    /*
     * A ListenerContainerFactory tells Spring how consumer should process the records
     * when performing consumption (including other features like the 'reattempt strategy')
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrderRequest> orderKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, OrderRequest> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        /*
         * This setting informs Spring that the orderConsumerFactory (above) should
         * be used to process events. @KafkaListener annotation must reference this
         * ListenerContainerFactory to use these settings.
         */
        factory.setConsumerFactory(orderConsumerFactory());

        /*
         * Here, we are setting the ack-mode to manual - scoped only for this
         * orderKafkaListenerContainerFactory.
         *
         * A global setting would be more appropriate for most apps:
         *      spring.kafka.listener.ack-mode=manual | set this in your application.properties.
         */
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);


        /*
         * The following sets the default error handling behavior for @KafkaListener
         * Events referencing this ListenerContainerFactory
         */
        factory.setCommonErrorHandler(orderErrorHandler());

        return factory;
    }

    /*
     * This 'Producer Factory' uses all default configuration settings except
     * for the Class Serializer - it uses a JSON Serializer (JacksonJsonSerializer)
     */
    @Bean
    public ProducerFactory<String, OrderRequest> orderProducerFactor() {
        Map<String, Object> configProps = kafkaProperties.buildProducerProperties();

        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JacksonJsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    /*
     * KafkaTemplate for use by the OrderProducer
     *
     * Note, this bean would be auto-wired (i.e. no definition needed)
     * if you only had a single producer expecting a single configuration
     * for a ProducerFactory (i.e. - since this application has a producer
     * for StringSerialization (GreetingProducer) and JsonSerialization
     * we need this.
     */
    @Bean
    public KafkaTemplate<String, OrderRequest> orderKafkaTemplate() {
        return new KafkaTemplate<> (orderProducerFactor());
    }

    /*
     * Creation of the 'orders' topic
     */
    @Bean
    public NewTopic ordersTopic() {
        return TopicBuilder.name("orders")
                .partitions(3)
                .replicas(2)    // 1 leader + 1 follower per partition
                .build();
    }


    /*
     * This bean details error handling behavior for this application
     */
    @Bean
    public DefaultErrorHandler orderErrorHandler() {
        /*
         * Retry Configuration. Note, you can only have one
         * 'backoff' strategy and one recoverer
         *
         * FixedBackOff: reattempt processing with a fixed delay
         *      new FixedBackOff( delay_in_ms , attempts);
         */
//        Retry the same message up to 3 times, with 2 second interval between
//        FixedBackOff backOff = new FixedBackOff(2000L, 3);

        ExponentialBackOff backOff = new ExponentialBackOff();
        backOff.setInitialInterval(2000L);   // first retry waits 2 seconds
        backOff.setMultiplier(2.0);          // each retry double the wait time (2, 4, 8 etc...)
        backOff.setMaxAttempts(3);           // maximum Attempts

        /*
         * When retried are exhausted, use this recoverer to route
         * the failed message to a Dead Letter Topic.
         *
         * This topic is given the name "<Topic-Name>.DLT" by default
         * i.e. 'orders.DLT'
         */
        DeadLetterPublishingRecoverer recoverer =
                new DeadLetterPublishingRecoverer(orderKafkaTemplate());

        return new DefaultErrorHandler(recoverer, backOff);
    }
}
