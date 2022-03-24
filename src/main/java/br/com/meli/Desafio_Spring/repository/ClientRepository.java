package br.com.meli.Desafio_Spring.repository;

import br.com.meli.Desafio_Spring.entity.Client;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClienteRepository {

    private List<Client> clients;

    private final String FILE_PATH="src/main/resources/clientes.json";
    private final ObjectMapper mapper = new ObjectMapper();

    public ClienteRepository() {
        this.clients = new ArrayList<>();
        getAllClientes();
    }

    public void getAllClientes(){
        try {
            clients.addAll(mapper.readValue(new File(FILE_PATH), new TypeReference<List<Client>>(){}));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Client> getAll() {
        return clients;
    }

    public Client save(Client client) {
        client.setId(clients.size() + 1);
        clients.add(client);
        return client;
    }
}
