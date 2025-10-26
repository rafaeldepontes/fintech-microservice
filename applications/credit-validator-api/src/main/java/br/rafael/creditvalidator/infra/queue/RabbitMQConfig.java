package br.rafael.creditvalidator.infra.queue;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${mq.queues.card-issuance-request}")
    private String queue;

    @Bean
    Queue cardRequestQueue() {
        return new Queue(queue, true);
    }

}
