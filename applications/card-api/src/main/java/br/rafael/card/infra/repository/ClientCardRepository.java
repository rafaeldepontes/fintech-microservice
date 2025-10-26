package br.rafael.card.infra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.rafael.card.domain.Client;

@Repository
public interface ClientCardRepository extends JpaRepository<Client, Long> {

    @EntityGraph(attributePaths = { "cards" })
    Optional<Client> findByCpf(String cpf);
}
