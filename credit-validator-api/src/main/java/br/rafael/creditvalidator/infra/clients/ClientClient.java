package br.rafael.creditvalidator.infra.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.rafael.creditvalidator.domain.Client;

@FeignClient(value = "client-api", path = "/api/v1/clients")
public interface ClientClient {

    @GetMapping(params = "cpf")
    public Client findByCpf(@RequestParam String cpf);

}