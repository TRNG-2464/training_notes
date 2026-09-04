package com.revature.springkafkademo.config;

import com.revature.springkafkademo.services.KafkaTopicInspector;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/*
 * The following example shows how Spring Kafka handles topic creation
 */
@Configuration
public class KafkaTopicConfig {

    /*
     * We can define a topic as a Spring Bean. On application startup
     * Spring Kafka's KafkaAdmin will automatically create this topic
     * (if it doesn't already exist) using these settings.
     */
    @Bean
    public NewTopic greetingsTopic() {
        return TopicBuilder.name("greetings")
                .partitions(3)
                .replicas(2)    // 1 leader + 1 follower per partition
                .build();
    }

    /*
     * The following method is just used to call the 'describeGreetingsTopic()' method
     * so we can see the output printed to the console.
     *
     * See the KafkaTopicInspector
     */
    @Bean
    public CommandLineRunner runInspector(KafkaTopicInspector inspector) {
        return args -> inspector.describeGreetingsTopic();
    }
}
