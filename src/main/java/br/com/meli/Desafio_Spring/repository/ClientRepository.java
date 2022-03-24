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
public class ClientRepository {

    private final String FILE_PATH="src/main/resources/clients.json";
    private final ObjectMapper mapper = new ObjectMapper();

    public List<Client> getAll() {
        try {
            List<Client> clients = mapper.readValue(new File(FILE_PATH), new TypeReference<List<Client>>(){});
            return clients;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public Client save(Client client) {
        List<Client> allClient;
        try {
            allClient = getAll();
            client.setId(allClient.size() + 1);
            allClient.add(client);
            mapper.writeValue(new File(FILE_PATH), allClient);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return client;
    }
}
