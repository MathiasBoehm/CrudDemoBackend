package de.struktuhr.crudbackend.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationEventPublisher {

    private final static Logger logger = LoggerFactory.getLogger(MyApplicationEventPublisher.class);

    private final ApplicationEventPublisher applicationEventPublisher;

    public MyApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishMessage(String message) {
        logger.info("publishMessage {}", message);
        CustomEvent customSpringEvent = new CustomEvent(this, message);
        applicationEventPublisher.publishEvent(customSpringEvent);
    }
}
