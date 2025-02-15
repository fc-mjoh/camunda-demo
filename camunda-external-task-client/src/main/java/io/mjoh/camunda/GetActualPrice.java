package io.mjoh.camunda;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.spin.SpinList;
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
        ExternalTaskClient client = ExternalTaskClient.create().baseUrl("http://localhost:8081/engine-rest")
                .asyncResponseTimeout(10000) // long polling timeout
                .build();

        // subscribe to an external task topic as specified in the process
        // the default lock duration is 20 seconds, but you can override this
        client.subscribe("get-quote").lockDuration(1000).handler((externalTask, externalTaskService) -> {

            Client restClient = ClientBuilder.newClient();
            WebTarget webTarget = restClient.target("http://localhost:8080/random");

            String t = webTarget.path("Ticker").queryParam("pair", "xbtusd").request()
                    .accept(MediaType.APPLICATION_JSON).get(String.class);

            SpinJsonNode json = S(t, json());
            SpinList<SpinJsonNode> lastTrade = json.prop("result").prop("XXBTZUSD").prop("c").elements();
            SpinJsonNode lastTradePrice = lastTrade.get(0);
            LOGGER.info("lastTradePrice = " + lastTradePrice.stringValue());

            Map<String, Object> hm = new HashMap<String, Object>();
            hm.put("lastTradePrice", Double.parseDouble(lastTradePrice.stringValue()));

            // Complete the task
            externalTaskService.complete(externalTask, hm);

        }).open();
    }

}
