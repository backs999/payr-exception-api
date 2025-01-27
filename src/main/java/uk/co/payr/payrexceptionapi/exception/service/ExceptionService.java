package uk.co.payr.payrexceptionapi.exception.service;

import uk.co.payr.payrexceptionapi.exception.model.event.ExceptionEvent;

public interface ExceptionService {

    void sendException(final ExceptionEvent exceptionEvent);

}
