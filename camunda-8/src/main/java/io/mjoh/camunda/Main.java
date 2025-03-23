package io.mjoh.camunda;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.spring.client.annotation.Deployment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@Deployment(resources = "classpath*:*.bpmn")
public class Main {

    private final ZeebeClient zeebeClient;

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public Main(ZeebeClient zeebeClient) {
        this.zeebeClient = zeebeClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    public void run(final String... args) {
        var bpmnProcessId = "coffee-process-camunda-8-v1"; // or whatever the key is
        var event = zeebeClient.newCreateInstanceCommand()
                .bpmnProcessId(bpmnProcessId)
                .latestVersion()
//                .variables(Map.of("total", 100))
                .send()
                .join();
        LOG.info(String.format("started a process: %d", event.getProcessInstanceKey()));
    }
}