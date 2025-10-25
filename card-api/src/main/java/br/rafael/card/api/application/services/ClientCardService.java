package br.rafael.card.api.application.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.rafael.card.api.application.models.ClientCardDTO;
import br.rafael.card.api.application.utils.CardUtils;
import br.rafael.card.domain.Client;
import br.rafael.card.infra.repository.ClientCardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class ClientCardService {
    
    private final ClientCardRepository repository;

    @Transactional(readOnly = true)
    public List<ClientCardDTO> findByCpf(String cpf) {
        log.info("[INFO] Listing cards by their owner's cpf: {}", cpf);
        Client client = repository.findByCpf(cpf).orElseThrow(() -> {
            log.error("[ERROR] No client found by cpf: {}", cpf);
            throw new RuntimeException("Client not found");
        });

        List<ClientCardDTO> clientCardsDTOs = new ArrayList<>();

        if (CardUtils.isNullOrEmpty(client.getCards())) {
            client.getCards().stream().forEach(cc -> {
                final ClientCardDTO clientCardDTO = ClientCardDTO
                    .builder()
                    .name(cc.getName())
                    .brand(cc.getBrand().toString())
                    .limit(cc.getIncomeLimit())
                    .build(); 
                clientCardsDTOs.add(clientCardDTO);
            });
        }

        log.info("[INFO] Listed {} cards", clientCardsDTOs.size());

        return clientCardsDTOs;
    }

}
