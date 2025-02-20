package io.mjoh.camunda;

import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.camunda.zeebe.spring.client.annotation.Deployment;
import io.camunda.zeebe.spring.client.config.ZeebeClientStarterAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableZeebeClient
@SpringBootApplication
@EnableScheduling
@Deployment(resources = "classpath*:*.bpmn")
@Import(value = {ZeebeClientStarterAutoConfiguration.class})
public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    public void run(final String... args) {
//        var bpmnProcessId = "camunda-8"; // or whatever the key is
//        var event = zeebeClient.newCreateInstanceCommand()
//                .bpmnProcessId(bpmnProcessId)
//                .latestVersion()
//                .variables(Map.of("total", 100))
//                .send()
//                .join();
//        LOG.info(String.format("started a process: %d", event.getProcessInstanceKey()));
    }
}