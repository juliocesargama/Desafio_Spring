package br.com.meli.Desafio_Spring.controller;

import br.com.meli.Desafio_Spring.dto.ClientDTO;
import br.com.meli.Desafio_Spring.entity.Client;
import br.com.meli.Desafio_Spring.entity.UF;
import br.com.meli.Desafio_Spring.exception.UfNotFoundException;
import br.com.meli.Desafio_Spring.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.util.EnumUtils;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor

public class ClientController {
    private final ClientService clientService;

    @PostMapping("/insert-client-request")
    public ResponseEntity<ClientDTO> createClients(@RequestBody @Valid ClientDTO clientDTO) {

        Client clientbyEmail = clientService.findByEmail(clientDTO.getEmail());

        if(clientbyEmail != null)
            return new ResponseEntity("Usuário já cadastrado", HttpStatus.BAD_REQUEST);

        Client client = clientService.save(clientDTO.convertToEntity());

        return new ResponseEntity(new ClientDTO(client), HttpStatus.CREATED);
    }

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getClients(@RequestParam(required = false) String uf) {

        if (!UF.isUF(uf)){
            throw new UfNotFoundException("UF not found");
        }
        UF ufEnum = UF.valueOf(uf);
        List<Client> clientes;

        clientes = ufEnum != null ?  clientService.findByUf(ufEnum) : clientService.findAll();

        return ResponseEntity.ok(clientes);
    }

}
