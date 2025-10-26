package br.rafael.creditvalidator.api.application.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.rafael.creditvalidator.api.application.models.ClientCardRequest;
import br.rafael.creditvalidator.api.application.models.ClientStatusInfo;
import br.rafael.creditvalidator.api.application.models.Protocol;
import br.rafael.creditvalidator.api.application.utils.CreditValidatorUtils;
import br.rafael.creditvalidator.domain.Card;
import br.rafael.creditvalidator.domain.CardDTO;
import br.rafael.creditvalidator.domain.Client;
import br.rafael.creditvalidator.domain.ClientStatus;
import br.rafael.creditvalidator.infra.clients.CardClient;
import br.rafael.creditvalidator.infra.clients.ClientClient;
import br.rafael.creditvalidator.infra.mqueue.CardRequestProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class CreditValidatorService {

    private final CardClient cardClient;
    private final ClientClient clientClient;
    private final CardRequestProducer cardRequestProducer;
    
    public ClientStatus checkClientStatus(String cpf) {
        log.info("[INFO] Listing client by cpf: {}", cpf);
        final Client client = clientClient.findByCpf(cpf);

        log.info("[INFO] Listing client's cards by cpf: {}", cpf);
        final List<Card> cards = cardClient.findByCpf(cpf).getBody();

        final ClientStatus clientStatus = ClientStatus
        .builder()
        .client(client)
        .cards(cards)
        .build();

        return clientStatus;
    }

    @SuppressWarnings("null")
    public List<Card> findAllCardsAvailable(ClientStatusInfo clientStatusInfo) {
        List<Card> cards = new ArrayList<>();
        
        log.info("[INFO] Listing client by cpf: {}", clientStatusInfo.getCpf());
        var client = clientClient.findByCpf(clientStatusInfo.getCpf());

        log.info("[INFO] Listing client by income: {}", clientStatusInfo.getIncome().toString());
        List<CardDTO> responseCardsByIncome = cardClient.findByIncome(clientStatusInfo.getIncome().longValue()).getBody();

        if (!CreditValidatorUtils.isNullOrEmpty(responseCardsByIncome)) {
            cards = responseCardsByIncome.stream().map(cDto -> {

                final BigDecimal basicLimit = cDto.getLimit();
                final BigDecimal age = BigDecimal.valueOf(client.getAge());
                final BigDecimal factor = age.divide(BigDecimal.TEN);

                final BigDecimal approvedLimit = factor.multiply(basicLimit);

                final Card card = Card
                    .builder()
                    .id(cDto.getId())
                    .name(cDto.getName())
                    .brand(cDto.getBrand())
                    .approvedLimit(approvedLimit)
                    .build();

                return card;
            }).toList();
        }

        return cards;
    }

    public Object sendCardRequest(ClientCardRequest cardRequest) throws JsonProcessingException {
        log.info("[INFO] Sending to Rabbit the cards information: {}", cardRequest.toString());
        cardRequestProducer.sendMessage(cardRequest);

        // Too lazy to implement a real protocol generation algorithm, but take this as one...
        return new Protocol(UUID.randomUUID().toString());
    }

}
