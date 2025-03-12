package io.mjoh.camunda.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ExecuteOrderDelegate implements JavaDelegate {

    private final Logger LOGGER = LoggerFactory.getLogger(ExecuteOrderDelegate.class);

    @Override
    public void execute(DelegateExecution delegateExecution) {
        LOGGER.info("Executing ExecuteOrderDelegate");
    }
}
