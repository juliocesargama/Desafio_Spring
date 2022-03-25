package br.com.meli.Desafio_Spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    private long id;
    private String name;
    private String email;
    private UF uf;
    private List<Long> idList;

    public Client(String name, String email, UF uf) {
        this.name = name;
        this.email = email;
        this.uf = uf;
        this.idList = new ArrayList<>();
    }
}
