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
        Client clientSaved = client.getId() == null ? create(client) : update(client);

        return clientSaved;
    }

    public List<Client> findByUf(UF uf) {
        List<Client> allClient = getAll();
        List<Client> clientStream = allClient.stream().filter(client ->  client.getUf() == uf).collect(Collectors.toCollection(ArrayList::new));

        return clientStream;
    }

    public void addPurchaseIdToList(Purchase purchase, Long id){
        Client client = findById(id);
        client.getIdList().add(purchase.getId());
        save(client);
    }

    public Client findById(Long id){
        List<Client> allClient = getAll();
        List<Client> clientStream = allClient.stream().filter(client ->  client.getId() == id).collect(Collectors.toCollection(ArrayList::new));

        if(clientStream.size() == 0)
            new EntityNotFoundException("Id not found " + id);

        return clientStream.get(0);
    }


    public Client update(Client client){
        List<Client> allClient = getAll();

        ArrayList<Client> collect = allClient.stream().map(clientBanco -> {
            if (clientBanco.getId() == client.getId()) {
                clientBanco = client;
            }
            return clientBanco;
        }).collect(Collectors.toCollection(ArrayList::new));

        escreverArquivo(collect);
        return client;
    }

    public Client create(Client client){
        List<Client> allClient = getAll();
        client.setId((long) (allClient.size() + 1));
        allClient.add(client);

        escreverArquivo(allClient);
        return client;
    }

    private void escreverArquivo(List<Client> allClient) {
        try {
            mapper.writeValue(new File(FILE_PATH), allClient);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
