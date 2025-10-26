package br.rafael.creditvalidator.infra.mqueue;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.rafael.creditvalidator.api.application.models.ClientCardRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@RequiredArgsConstructor
@Log4j2
public class CardRequestProducer {
    
    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;
    private final ObjectMapper objectMapper;

    public void sendMessage(ClientCardRequest clientCardRequest) throws JsonProcessingException {
        var json = objectMapper.writeValueAsString(clientCardRequest);
        log.info("[INFO] Card Request serialization completed, {}", json);
        rabbitTemplate.convertAndSend(queue.getName(), json);
    }

}
