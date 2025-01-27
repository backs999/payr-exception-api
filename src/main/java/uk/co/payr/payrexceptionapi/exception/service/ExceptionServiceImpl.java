package uk.co.payr.payrexceptionapi.exception.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uk.co.payr.payrexceptionapi.exception.data.ExceptionRepository;
import uk.co.payr.payrexceptionapi.exception.model.StoredException;
import uk.co.payr.payrexceptionapi.exception.model.event.ExceptionEvent;

@Service
@AllArgsConstructor
public class ExceptionServiceImpl implements ExceptionService {

    private final ExceptionRepository exceptionRepository;

    @Override
    public void sendException(ExceptionEvent exceptionEvent) {

        exceptionRepository.save(StoredException.builder()
                        .message(exceptionEvent.getMessage())
                        .timestamp(exceptionEvent.getTimestamp())
                        .service(exceptionEvent.getService())
                        .exception(exceptionEvent.getException())
                .build());
    }
}
