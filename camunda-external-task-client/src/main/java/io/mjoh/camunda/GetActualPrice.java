package io.mjoh.camunda;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import org.camunda.bpm.client.ExternalTaskClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class GetActualPrice {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetActualPrice.class);

    public static void main(String[] args) {
        ExternalTaskClient client = ExternalTaskClient.create().baseUrl("http://localhost:8081/engine-rest")
                .asyncResponseTimeout(10000) // long polling timeout
                .build();

        // subscribe to an external task topic as specified in the process
        // the default lock duration is 20 seconds, but you can override this
        client.subscribe("get-coffee-price")
                .lockDuration(1000)
                .handler((externalTask, externalTaskService) -> {

                    WebTarget webTarget;
                    Client restClient = ClientBuilder.newClient();
                    webTarget = restClient.target("http://localhost:8080/random");


                    String t = webTarget.request()
                            .accept(MediaType.TEXT_PLAIN).get(String.class);

                    LOGGER.info("lastTradePrice = {}", t);

                    Map<String, Object> hm = new HashMap<>();
                    hm.put("lastTradePrice", Double.parseDouble(t));

                    // Complete the task
                    externalTaskService.complete(externalTask, hm);

                }).open();
    }

}
