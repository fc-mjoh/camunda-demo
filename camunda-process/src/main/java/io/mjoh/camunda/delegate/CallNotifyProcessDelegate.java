package io.mjoh.camunda.delegate;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class CallNotifyProcessDelegate implements JavaDelegate {

    private final Logger LOGGER = LoggerFactory.getLogger(CallNotifyProcessDelegate.class);

    private final RuntimeService runtimeService;

    public CallNotifyProcessDelegate(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) {
        LOGGER.info("NotifyProcessDelegate: {}", "message");
        Object message = delegateExecution.getVariable("message");

        LOGGER.info("NotifyProcessDelegate: {}", message);

        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("message", message);

        runtimeService.startProcessInstanceByMessage("message_notify", stringObjectHashMap);
    }
}
