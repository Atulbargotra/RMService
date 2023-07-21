package dev.atul.rmworker.mq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.atul.rmworker.api.view.ViewApi;
import dev.atul.rmworker.dto.RMEvent;
import dev.atul.rmworker.util.RMUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class RabbitMQConsumer {
    private final ViewApi viewApi;
    private final RMUtil rmUtil;
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

    public RabbitMQConsumer(ViewApi viewApi, RMUtil rmUtil) {
        this.viewApi = viewApi;
        this.rmUtil = rmUtil;
    }

    @RabbitListener(queues = {"${rabbitmq.queue.request.name}"})
    public void consume(String message){
        ObjectMapper mapper = new ObjectMapper();
        try {
            RMEvent rmEvent = mapper.readValue(message, RMEvent.class);

            CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
                rmUtil.pushResponse(rmEvent.getRequestId(), viewApi.sendRMEvent(rmEvent));
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
