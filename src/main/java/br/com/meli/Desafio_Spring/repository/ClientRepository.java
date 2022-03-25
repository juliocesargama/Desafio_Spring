package br.com.meli.Desafio_Spring.repository;

import br.com.meli.Desafio_Spring.entity.Client;
import br.com.meli.Desafio_Spring.entity.Purchase;
import br.com.meli.Desafio_Spring.entity.UF;
import br.com.meli.Desafio_Spring.exception.EntityNotFoundException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ClientRepository {

    private final String FILE_PATH="src/main/resources/clients.json";
    private final ObjectMapper mapper = new ObjectMapper();
    private List<Client> clients = getAll();

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

    public List<Client> findByUf(UF uf) {
        List<Client> allClient = getAll();
        List<Client> clientStream = allClient.stream().filter(client ->  client.getUf() == uf).collect(Collectors.toCollection(ArrayList::new));

        return clientStream;
    }

    public void addPurchaseIdToList(Purchase purchase, long idCliente){
        Client client = clients.stream()
                        .filter(a -> a.getId() == idCliente)
                        .findFirst().orElseThrow(() -> new EntityNotFoundException("Id not found " + idCliente));
        if (client.getIdList() == null){
            client.setIdList(new ArrayList<>());
        }
        client.getIdList().add((Long) purchase.getId());
    }
}
