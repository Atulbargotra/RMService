package dev.atul.rmworker.util;

import dev.atul.rmworker.dto.RMEventResponse;
import dev.atul.rmworker.dto.ViewRMResponse;
import dev.atul.rmworker.mq.RabbitMQProducer;
import org.springframework.stereotype.Component;

@Component
public class RMUtil {

    private final RabbitMQProducer producer;

    public RMUtil(RabbitMQProducer producer) {
        this.producer = producer;
    }


    public void pushResponse(String requestId, ViewRMResponse viewRMResponse) {
        RMEventResponse rmEventResponse = new RMEventResponse();
        rmEventResponse.setRequestId(requestId);
        rmEventResponse.setStatus(viewRMResponse.getResponse());
        rmEventResponse.setErrorMessage(viewRMResponse.getError());
        producer.sendMessage(rmEventResponse);
    }

}
