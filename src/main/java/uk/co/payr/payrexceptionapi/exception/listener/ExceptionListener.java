package uk.co.payr.payrusersapi.user.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.flogger.Flogger;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;
import uk.co.payr.payrusersapi.user.model.User;
import uk.co.payr.payrusersapi.user.model.event.UserUpdateEvent;
import uk.co.payr.payrusersapi.user.service.UserUpdateService;

@Component
@RequiredArgsConstructor
@Flogger
public class UserUpdateListener {

    private final ObjectMapper mapper;
    private final UserUpdateService userUpdateService;

    @RetryableTopic(attempts = "5", backoff = @Backoff(delay = 5000))
    @KafkaListener(topics = "${payr.kafka.topic-update-user}", groupId = "payr-users-api")
    public void listens(final String updateUser) {
        try {
            final var update = mapper.readValue(updateUser, UserUpdateEvent.class);
            log.atInfo().log("User update received: " + update.getUserId());
            userUpdateService.externalUserUpdate(update);
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