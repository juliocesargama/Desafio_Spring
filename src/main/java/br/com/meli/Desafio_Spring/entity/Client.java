package br.com.meli.Desafio_Spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {


    private long id;
    private String name;
    private String email;
    private Federacao federacao;
}
