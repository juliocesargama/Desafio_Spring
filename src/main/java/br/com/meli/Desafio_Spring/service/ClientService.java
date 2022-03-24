package br.com.meli.Desafio_Spring.service;

import br.com.meli.Desafio_Spring.entity.Client;
import br.com.meli.Desafio_Spring.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientService {

  @Autowired
  private final ClientRepository repository;

    public List<Client> findAll() {
        return repository.getAll();
    }

    public Client save(Client client) {
        repository.save(client);
        return client;
    }

}
