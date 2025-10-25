package br.rafael.card.api.application.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.rafael.card.api.application.models.CardDTO;
import br.rafael.card.api.application.models.ClientCard;
import br.rafael.card.domain.Card;
import br.rafael.card.domain.enums.CardBrand;
import br.rafael.card.infra.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class CardService {
    
    private final CardRepository repository;

    @Transactional
    public void create(CardDTO cardDTO) {
        log.info("[INFO] Creating card: {}", cardDTO);
        final Card card = Card
            .builder()
            .name(cardDTO.getName())
            .brand(CardBrand.valueOf(cardDTO.getBrand()))
            .income(cardDTO.getIncome())
            .incomeLimit(cardDTO.getLimit())
            .build();

        repository.save(card);
        log.info("[INFO] Card created successfully, id: {}", card.getId());
    }

    public List<CardDTO> findByIncome(Long income) {
        log.info("[INFO] Listing cards by their income: {}", income.toString());
        var cards = repository.findByIncomeLessThanEqual(BigDecimal.valueOf(income));

        List<CardDTO> cardsDTO = new ArrayList<>();

        cards.stream().forEach(c -> {
            final CardDTO cardDTO = CardDTO
                .builder()
                .id(c.getId())
                .name(c.getName())
                .brand(c.getBrand().toString())
                .income(c.getIncome())
                .limit(c.getIncomeLimit())
                .build(); 
            cardsDTO.add(cardDTO);
        });

        log.info("[INFO] Listed {} cards", cardsDTO.size());

        return cardsDTO;
    }

    // WIP
    public List<ClientCard> findByCpf(String cpf) {
        log.info("[INFO] Listing cards by their owner's cpf: {}", cpf);
        

        List<ClientCard> clientCards = new ArrayList<>();

        return clientCards;
    }

}
