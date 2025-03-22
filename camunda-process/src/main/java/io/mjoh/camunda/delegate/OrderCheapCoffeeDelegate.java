package io.mjoh.camunda.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class OrderCheapCoffeeDelegate implements JavaDelegate {
    private final Logger LOGGER = LoggerFactory.getLogger(NotifyDelegate.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        LOGGER.info("Executing OrderCheapCoffeeDelegate");
    }
}
