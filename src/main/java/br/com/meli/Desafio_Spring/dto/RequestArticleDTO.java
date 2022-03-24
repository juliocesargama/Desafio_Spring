package br.com.meli.Desafio_Spring.dto;

import lombok.Data;

import java.util.List;

@Data
public class TesteDTO {
    private List<ArticleInputDTO> articles;
}

