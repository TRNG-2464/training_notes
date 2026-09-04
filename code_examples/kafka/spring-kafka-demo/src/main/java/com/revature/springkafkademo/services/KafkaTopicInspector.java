package com.revature.springkafkademo.services;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.common.TopicPartitionInfo;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaTopicInspector {

    private final KafkaAdmin kafkaAdmin;

    public KafkaTopicInspector(KafkaAdmin kafkaAdmin) {
        this.kafkaAdmin = kafkaAdmin;
    }

    public void describeGreetingsTopic() {

        try (AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties())) {

            DescribeTopicsResult result = adminClient.describeTopics(List.of("greetings"));
            TopicDescription description = result.topicNameValues().get("greetings").get();

            for (TopicPartitionInfo partitionInfo : description.partitions()) {
                System.out.printf(
                        "Partition: %d | Leader: %s | Replicas: %s | ISR: %s%n",
                        partitionInfo.partition(),
                        partitionInfo.leader().id(),
                        partitionInfo.replicas(),
                        partitionInfo.isr()
                );
            }

        } catch (Exception e) {
            /*
             * Cluster metadata calls can fail if the broker is unreachable
             * or the topic doesn't exist yet. As such, we should always
             * handle potential exceptions
             */
            throw new RuntimeException("Failed to describe topic", e);
        }
    }
}
