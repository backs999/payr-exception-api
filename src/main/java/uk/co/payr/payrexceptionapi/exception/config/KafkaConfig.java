package uk.co.payr.payrusersapi.user.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic newOrderTopic(final KafkaConfigProps kafkaConfigProps) {
        return TopicBuilder.name(kafkaConfigProps.getTopicOrdersNew())
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic orderErrorTopic(final KafkaConfigProps kafkaConfigProps) {
        return TopicBuilder.name(kafkaConfigProps.getTopicOrdersError())
                .partitions(10)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic notificationTopic(final KafkaConfigProps kafkaConfigProps) {
        return TopicBuilder.name(kafkaConfigProps.getTopicNotificationNew())
                .partitions(10)
                .replicas(1)
                .build();
    }
}
