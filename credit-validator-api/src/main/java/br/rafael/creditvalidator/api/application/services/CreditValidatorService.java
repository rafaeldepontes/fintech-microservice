package br.rafael.creditvalidator.api.application.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import br.rafael.creditvalidator.api.application.models.ClientStatusInfo;
import br.rafael.creditvalidator.api.application.utils.CreditValidatorUtils;
import br.rafael.creditvalidator.domain.Card;
import br.rafael.creditvalidator.domain.CardDTO;
import br.rafael.creditvalidator.domain.Client;
import br.rafael.creditvalidator.domain.ClientStatus;
import br.rafael.creditvalidator.infra.clients.CardClient;
import br.rafael.creditvalidator.infra.clients.ClientClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class CreditValidatorService {

    private final CardClient cardClient;
    private final ClientClient clientClient;
    
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
        final List<Card> cards = new ArrayList<>();
        
        log.info("[INFO] Listing client by cpf: {}", clientStatusInfo.getCpf());
        var responseCardsByCpf = cardClient.findByCpf(clientStatusInfo.getCpf()).getBody();

        if (!CreditValidatorUtils.isNullOrEmpty(responseCardsByCpf)) {
            responseCardsByCpf.stream().map(cards::add);
            responseCardsByCpf = null;
        }

        log.info("[INFO] Listing client by income: {}", clientStatusInfo.getIncome().toString());
        List<CardDTO> responseCardsByIncome = cardClient.findByIncome(clientStatusInfo.getIncome().longValue()).getBody();

        if (!CreditValidatorUtils.isNullOrEmpty(responseCardsByIncome)) {
            responseCardsByIncome.stream().forEach(cDto -> {
                final Card card = Card
                    .builder()
                    .name(cDto.getName())
                    .brand(cDto.getBrand())
                    .approvedLimit(cDto.getLimit())
                    .build();

                cards.add(card);
            });
            responseCardsByIncome = null;
        }

        return cards;
    }

    

}
