package br.com.meli.Desafio_Spring.dto;

import br.com.meli.Desafio_Spring.entity.Client;
import br.com.meli.Desafio_Spring.entity.UF;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    private long id;

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    private UF uf;

    public ClientDTO(Client client) {
        id = client.getId();
        name = client.getName();
        email = client.getEmail();
        uf = client.getUf();
    }

    public Client convertToEntity(){
        return new Client(name, email, uf);
    }
}
