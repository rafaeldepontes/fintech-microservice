package br.rafael.card.domain.infra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.rafael.card.domain.Card;
import java.math.BigDecimal;


@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findByIncomeLessThanEqual(BigDecimal income);
    
    List<Card> findByName(String name);
}
