package uk.co.payr.payrexceptionapi.exception.config;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;


@Configuration
@ConfigurationProperties(prefix = "payr.kafka")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class KafkaConfigProps {

    @NotNull
    private String topicException;
}