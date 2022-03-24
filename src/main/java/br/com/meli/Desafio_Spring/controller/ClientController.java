package br.com.meli.Desafio_Spring.controller;

import br.com.meli.Desafio_Spring.dto.ClientDTO;
import br.com.meli.Desafio_Spring.entity.Client;
import br.com.meli.Desafio_Spring.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor

public class ClientController {
    private final ClientService clientService;

    @PostMapping("/insert-client-request")
    public ResponseEntity<ClientDTO> createClients(@RequestBody @Valid ClientDTO clientDTO) {

        Client client = clientService.save(clientDTO.convertToEntity());

        return new ResponseEntity(new ClientDTO(client), HttpStatus.CREATED);
    }

    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return clientService.findAll();
    }

}
