package io.mjoh.camunda.delegate;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class NotifyDelegate implements JavaDelegate {

    private final Logger LOGGER = LoggerFactory.getLogger(NotifyDelegate.class);

    @Override
    public void execute(DelegateExecution delegateExecution) {
        String message = (String) delegateExecution.getVariable("message");
        LOGGER.info("NotifyProcessDelegate: {}", message);
        if (message.toLowerCase().contains("boss")) {
            LOGGER.info("Chef found");
            throw new BpmnError("he_is_watching_you");
        } else if (message.toLowerCase().contains("error")) {
            throw new RuntimeException("Error");
        }
    }
}
