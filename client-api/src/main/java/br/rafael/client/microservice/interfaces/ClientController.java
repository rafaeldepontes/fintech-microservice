package br.rafael.client.microservice.interfaces;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.rafael.client.microservice.application.models.ClientDTO;
import br.rafael.client.microservice.application.services.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/v1/clients")
@Log4j2
@RequiredArgsConstructor
public class ClientController {

    private final ClientService service;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ClientDTO findByCpf(@RequestParam String cpf) {
        return service.findByCpf(cpf);
    }

    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody ClientDTO clientDTO) {

        service.create(clientDTO);

        var uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .query("cpf={cpf}")
            .buildAndExpand(clientDTO.getCpf())
            .toUri();

        return ResponseEntity.created(uri).build();
    }
    

}
