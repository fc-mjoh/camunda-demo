package io.mjoh.camunda;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.spin.json.SpinJsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static org.camunda.spin.DataFormats.json;
import static org.camunda.spin.Spin.S;

public class GetActualPrice {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetActualPrice.class);

    public static void main(String[] args) {
        ExternalTaskClient client = ExternalTaskClient.create().baseUrl("http://localhost:8080/engine-rest")
                .asyncResponseTimeout(10000) // long polling timeout
                .build();

        client.subscribe("get-coffee-price")
                .lockDuration(1000)
                .handler((externalTask, externalTaskService) -> {

                    WebTarget webTarget;
                    Client restClient = ClientBuilder.newClient();
                    webTarget = restClient.target("http://localhost:9080/random-json");

                    String t = webTarget.request()
                            .accept(MediaType.APPLICATION_JSON).get(String.class);

                    SpinJsonNode json = S(t, json());
                    Number price = json.prop("price").numberValue();

                    LOGGER.info("lastTradePrice = {}", price);

                    Map<String, Object> hm = new HashMap<>();
                    hm.put("lastTradePrice", price.longValue());

                    // Complete the task
                    externalTaskService.complete(externalTask, hm);

                }).open();
    }

}
