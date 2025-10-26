package br.rafael.card.infra.mqueue;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.rafael.card.api.application.utils.CardUtils;
import br.rafael.card.domain.Card;
import br.rafael.card.domain.CardRequestDTO;
import br.rafael.card.domain.Client;
import br.rafael.card.infra.repository.CardRepository;
import br.rafael.card.infra.repository.ClientCardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@RequiredArgsConstructor
@Log4j2
public class CardRequestConsumer {

    private final CardRepository cardRepository;
    private final ClientCardRepository clientCardRepository;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "${mq.queues.card-issuance-request}")
    @Transactional
    void cardRequestListener(@Payload String payload) throws JsonMappingException, JsonProcessingException {
        log.info("[INFO] payload to deserialize {}", payload);
        var cardRequest = objectMapper.readValue(payload, CardRequestDTO.class);
        log.info("[INFO] Card Request deserialization completed, {}", cardRequest.toString());

        final Client client = clientCardRepository.findByCpf(cardRequest.getCpf()).orElse(new Client());

        final Card card = cardRepository.findById(cardRequest.getCardId()).orElseThrow(() -> {
            log.error("[ERROR] Card id invalid");
            throw new RuntimeException("Card not found");
        });

        client.setCpf(cardRequest.getCpf());
        client.setIncomeLimit(cardRequest.getApprovedLimit());
        client.setAddress(cardRequest.getAddress());
        if (CardUtils.isNullOrEmpty(client.getCards())) {
            client.setCards(new ArrayList<>());
        }
        final List<Card> cards = client.getCards();
        card.setClient(client);
        cards.add(card);
        client.setCards(cards);

        clientCardRepository.save(client);
        log.info("[INFO] Client saved successfully cpf: {} and id: {}", client.getCpf(), client.getId());
    }

}