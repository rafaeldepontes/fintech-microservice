package br.rafael.client.api.application.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.rafael.client.api.application.models.ClientDTO;
import br.rafael.client.api.domain.Client;
import br.rafael.client.api.infra.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class ClientService {
    
    private final ClientRepository repository;

    @Transactional
    public void create(ClientDTO clientDTO) {
        log.info("[INFO] Creating client - {}", clientDTO.toString());
        final Client client = Client
            .builder()
            .id(clientDTO.getId())
            .name(clientDTO.getName())
            .age(clientDTO.getAge())
            .cpf(clientDTO.getCpf())
            .build();
        
        repository.save(client);
    }

    @Transactional(readOnly = true)
    public ClientDTO findByCpf(String cpf) {
        log.info("[INFO] Searching for client's cpf: {}", cpf);
        final Client client = repository.findByCpf(cpf).orElseThrow(() -> {
            log.error("[ERROR] Client not found, cpf: {}", cpf);
            throw new RuntimeException("Client not found");
        });
        
        log.info("[INFO] Client found - {}", client.toString());
        return ClientDTO
            .builder()
            .id(client.getId())
            .name(client.getName())
            .age(client.getAge())
            .cpf(client.getCpf())
            .build();
    }

}
