package uk.co.payr.payrexceptionapi.exception.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.flogger.Flogger;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;
import uk.co.payr.payrexceptionapi.exception.model.event.ExceptionEvent;
import uk.co.payr.payrexceptionapi.exception.service.ExceptionService;

@Component
@RequiredArgsConstructor
@Flogger
public class ExceptionListener {

    private final ObjectMapper mapper;
    private final ExceptionService exceptionService;

    @RetryableTopic(attempts = "5", backoff = @Backoff(delay = 5000))
    @KafkaListener(topics = "${payr.kafka.topic-exception}", groupId = "payr-exception-api")
    public void listens(final String incomingException) {
        try {
            final var exception = mapper.readValue(incomingException, ExceptionEvent.class);
            log.atInfo().log("Exception received from topic: " + exception.getService());
            exceptionService.sendException(exception);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @DltHandler
    public void handleDlt(String incomingOrder) {
        log.atSevere().log("Failed to process order after retries: {}", incomingOrder);
        // Handle the message that failed after retries
    }
}