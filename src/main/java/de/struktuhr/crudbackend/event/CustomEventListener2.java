package de.struktuhr.crudbackend.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CustomEventListener2 implements ApplicationListener<CustomEvent> {

    private final static Logger logger = LoggerFactory.getLogger(CustomEventListener2.class);

    @Override
    public void onApplicationEvent(CustomEvent customEvent) {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("onApplicationEvent {}", customEvent.getMessage());
    }
}
